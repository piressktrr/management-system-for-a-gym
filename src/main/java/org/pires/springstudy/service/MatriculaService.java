package org.pires.springstudy.service;

import jakarta.transaction.Transactional;
import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.MatriculaResponse;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;
import org.pires.springstudy.infrastructure.exceptions.AlunoHasACobrancaVencida;
import org.pires.springstudy.infrastructure.exceptions.InvalidMatriculaCancellationException;
import org.pires.springstudy.infrastructure.exceptions.ResourceNotFoundException;
import org.pires.springstudy.infrastructure.repository.CobrancaRepository;
import org.pires.springstudy.infrastructure.repository.ContratoRepository;
import org.pires.springstudy.infrastructure.repository.MatriculaPlanoRepository;
import org.pires.springstudy.infrastructure.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    private final CobrancaRepository cobrancaRepository;

    private final MatriculaPlanoRepository matriculaPlanoRepository;

    private final ContratoRepository contratoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            CobrancaRepository cobrancaRepository,
                            MatriculaPlanoRepository matriculaPlanoRepository,
                            ContratoRepository contratoRepository) {

        this.matriculaRepository = matriculaRepository;
        this.cobrancaRepository = cobrancaRepository;
        this.matriculaPlanoRepository = matriculaPlanoRepository;
        this.contratoRepository = contratoRepository;
    }

    public Matricula saveMatricula(MatriculaRequest matriculaRequest, Aluno aluno) {

         return matriculaRepository.save(
                new Matricula(aluno,
                        matriculaRequest.descricaoSaude(),
                        LocalDateTime.now(),
                        BigDecimal.valueOf(60.00),
                        StatusMatricula.ATIVO,
                        "",
                        null)
        );
    }

    // o service busca essa matrícula e usa suas relações para chegar ao aluno (`matricula.getAluno()`),
    // ao contrato e ao vínculo atual em `MatriculaPlano`
    // Este metodo coordena várias alterações relacionadas.
    // A transação garante que todas sejam persistidas juntas ou nenhuma delas.
    @Transactional
    public void cancelarMatricula(UUID matriculaId, String motivoCancelamento) {
        var matricula = matriculaRepository.findById(matriculaId).orElseThrow(() ->
                new ResourceNotFoundException("Matricula não encontrada!"));

        if (matricula.getStatus() != StatusMatricula.ATIVO) {
            throw new InvalidMatriculaCancellationException(
                    "Somente uma matrícula ativa pode ser cancelada.");
        }


        // Uma matrícula pode ter vínculos históricos, mas deve ter um único
        // vínculo atual (dataFim nula). Não escolhemos um vínculo ao acaso.
        var vinculosAtuais =
                matriculaPlanoRepository.findAllByMatriculaAndDataFimIsNull(matricula);

        if (vinculosAtuais.size() != 1) {
            throw new InvalidMatriculaCancellationException(
                    "A matrícula deve ter exatamente um vínculo de plano atual.");
        }

        var matriculaPlano = vinculosAtuais.get(0);

        var contrato = contratoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new InvalidMatriculaCancellationException(
                                "A matrícula não possui contrato para encerrar."));


        // A regra é baseada em dia de calendário no fuso de Brasília:
        // vencimento hoje não é dívida vencida; só uma data anterior bloqueia.

        var cobrancasPendentes =
                cobrancaRepository.findAllByIdMatriculaPlano_Matricula_IdAndStatusCobranca(
                        matriculaId, StatusCobranca.PENDENTE);

        boolean existeCobrancaVencida = cobrancasPendentes.stream()
                .map(Cobranca::getDataDeVencimento)
                .map(LocalDateTime::toLocalDate)
                .anyMatch(dataVencimento -> dataVencimento.isBefore(LocalDate.now(ZoneId.of("America/Sao-Paulo"))));

        if (existeCobrancaVencida) {
            throw new InvalidMatriculaCancellationException(
                    "Há cobrança vencida; a matrícula não pode ser cancelada.");
        }

        // Pendências ainda não vencidas são encerradas, mas continuam no histórico.
        cobrancasPendentes.forEach(
                cobranca -> cobranca.setStatusCobranca(StatusCobranca.CANCELADA));
        cobrancaRepository.saveAll(cobrancasPendentes);

        // Atualiza o vínculo individual do aluno com o plano, sem alterar
        // o registro do plano compartilhado no catálogo.
        matriculaPlano.setDataFim(dataCancelamento);
        matriculaPlanoRepository.save(matriculaPlano);

        // Encerra o contrato associado à matrícula.
        contrato.setDataEncerramento(dataHoraCancelamento);
        contratoRepository.save(contrato);

        // Registra o cancelamento na matrícula e preserva seu histórico.
        matricula.setStatus(StatusMatricula.CANCELADA);
        matricula.setMotivoDesligamento(motivoCancelamento);
        matricula.setDataDesligamento(dataHoraCancelamento);
        matriculaRepository.save(matricula);
    }
}
