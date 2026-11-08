package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;
import org.pires.springstudy.infrastructure.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

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



}
