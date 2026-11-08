package org.pires.springstudy.service;

import jakarta.transaction.Transactional;
import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.MatriculaResponse;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;
import org.pires.springstudy.infrastructure.exceptions.AlunoHasACobrancaVencida;
import org.pires.springstudy.infrastructure.exceptions.ResourceNotFoundException;
import org.pires.springstudy.infrastructure.repository.CobrancaRepository;
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
    @Autowired
    private CobrancaRepository cobrancaRepository;
    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
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
    @Transactional
    public MatriculaResponse cancelarMatricula(UUID matriculaId, String motivoCancelamento) {
        var b = matriculaRepository.findById(matriculaId).orElseThrow(() -> new ResourceNotFoundException("Matricula não encontrada!"));

        var c = cobrancaRepository.findAlunosByStatusCobrancaPendenteAndDataVencimentoBeforeActualDate(
                        StatusCobranca.PENDENTE, LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))) // aplica o fuso do horario de brasilia
                .stream()
                .toList();

        for (Aluno aluno : c) {
            if (b.getAluno().equals((aluno))) {
                throw new IllegalArgumentException("Impossivel de cancelar matricula, pois há uma cobrança para o aluno");
            }
        }


    }
}
