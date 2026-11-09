package org.pires.springstudy.service;

import jakarta.transaction.Transactional;
import org.pires.springstudy.dtos.request.MotivoCancelamentoRequest;
import org.pires.springstudy.dtos.request.alunoSearch.MatriculaAlunoDadosRequest;
import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
import org.pires.springstudy.dtos.response.alunoSearch.MatriculaResponseSearch;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;
import org.pires.springstudy.infrastructure.exceptions.AlunoNotFoundException;
import org.pires.springstudy.infrastructure.exceptions.IllegalCredentialsException;
import org.pires.springstudy.infrastructure.exceptions.InvalidMatriculaCancellationException;
import org.pires.springstudy.infrastructure.exceptions.ResourceNotFoundException;
import org.pires.springstudy.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    private final CobrancaRepository cobrancaRepository;

    private final MatriculaPlanoRepository matriculaPlanoRepository;

    private final ContratoRepository contratoRepository;

    @Autowired
    private AlunoRepository alunoRepository;
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
                        StatusMatricula.ATIVA,
                        "",
                        null)
        );
    }

    @Transactional // transactional pq se der erro ele nao altera nada
    public void alterarDadosMatricula(UUID matriculaId, MatriculaAlunoDadosRequest matriculaAlunoDadosRequest) {
        var telefone = matriculaAlunoDadosRequest.telefone().replaceAll("\\D", "");
        var email = matriculaAlunoDadosRequest.email().toLowerCase();

        Matricula matricula = matriculaRepository.findById(matriculaId).orElseThrow(() ->
                new ResourceNotFoundException("Matricula não encontrada"));

        if (matricula.getStatus() != StatusMatricula.ATIVA) {
            throw new IllegalArgumentException("Uma matricula cancelada ou inativa não pode ser alterada");
        }

        if(alunoRepository.existsByEmail(email) || alunoRepository.existsByTelefone(telefone)) {
            throw new IllegalCredentialsException("Já existe um aluno com essas credenciais!!");
        }



//        if (!matriculaAlunoDadosRequest.descricaoSaude().isEmpty()) {
//            matricula.setDescricaoSaude(matriculaAlunoDadosRequest.descricaoSaude());
//        }
//        if (!matriculaAlunoDadosRequest.email().isBlank()) {
//            matricula.getAluno().setEmail(matriculaAlunoDadosRequest.email());
//        }
//        if(!matriculaAlunoDadosRequest.telefone().isEmpty()) {
//            matricula.getAluno().setTelefone(matriculaAlunoDadosRequest.telefone());
//        }

        matriculaRepository.save(matricula);
    }

    // Este metodo coordena várias alterações relacionadas.
    // A transação garante que todas sejam persistidas juntas ou nenhuma delas.
    @Transactional
    public void cancelarMatricula(UUID matriculaId, MotivoCancelamentoRequest motivoCancelamentoRequest) {
        // O service busca  matrícula e usa suas relações para chegar ao aluno (`matricula.getAluno()`),
        // Ao contrato e ao vínculo atual em `MatriculaPlano`.

        // TIVE MUITA AJUDA DA I.A NESSE METODO
        var matricula = matriculaRepository.findById(matriculaId).orElseThrow(() ->
                new ResourceNotFoundException("Matricula não encontrada!"));

        if (matricula.getStatus() != StatusMatricula.ATIVA) {
            throw new InvalidMatriculaCancellationException(
                    "Somente uma matrícula ativa pode ser cancelada.");
        }


        // Uma matrícula pode ter vínculos históricos, mas deve ter um único
        // vínculo atual (dataFim nula). Não escolhemos um vínculo ao acaso.
        var todasAsMatriculasPlanosAtuais =
                matriculaPlanoRepository.findAllByMatriculaAndDataFimIsNull(matricula);

        if (todasAsMatriculasPlanosAtuais.size() != 1) {
            throw new InvalidMatriculaCancellationException(
                    "A matrícula deve ter exatamente um vínculo de plano atual.");
        }

        var matriculaPlano = todasAsMatriculasPlanosAtuais.getFirst();

        var contrato = contratoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new InvalidMatriculaCancellationException(
                                "A matrícula não possui contrato para encerrar."));


        // A regra é baseada em dia de calendário no fuso de Brasília:
        // Vencimento hoje não é dívida vencida; só uma data anterior bloqueia.

        var cobrancasPendentes =
                cobrancaRepository.findAllByIdMatriculaPlano_Matricula_IdAndStatusCobranca(
                        matriculaId, StatusCobranca.PENDENTE);

        boolean existeCobrancaVencida =
                cobrancasPendentes.stream()
                .map(Cobranca::getDataDeVencimento)
                .anyMatch(dataVencimento -> dataVencimento.isBefore(LocalDate.now(ZoneId.of("America/Sao_Paulo"))));

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
        matriculaPlano.setDataFim(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        matriculaPlanoRepository.save(matriculaPlano);

        // Encerra o contrato associado à matrícula.
        contrato.setDataEncerramento(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        contratoRepository.save(contrato);

        // Registra o cancelamento na matrícula e preserva seu histórico.
        matricula.setStatus(StatusMatricula.CANCELADA);
        matricula.setMotivoDesligamento(motivoCancelamentoRequest.motivoCancelamento());
        matricula.setDataDesligamento(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        matriculaRepository.save(matricula);
    }


    public List<MatriculaResponseSearch> searchAllMatriculasByAluno(UUID alunoId) {

        if (alunoRepository.existsById(alunoId)) {
            var aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado!! "));
            var lista = matriculaRepository.findAllMatriculasByAluno(aluno.getId());

            return lista.stream().map(m ->
                            new MatriculaResponseSearch(
                                    m.getId(),
                                    new AlunoResponseSearch(
                                            m.getAluno().getId(),
                                            m.getAluno().getNome(),
                                            m.getAluno().getSobrenome(),
                                            m.getAluno().getSexo()),
                                    m.getDataMatricula(),
                                    m.getStatus()))
                    .toList();
        } else {
            throw new AlunoNotFoundException("ID de aluno não existe na base de dados!");
        }

    }
}
