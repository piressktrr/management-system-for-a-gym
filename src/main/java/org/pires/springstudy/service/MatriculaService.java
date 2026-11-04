package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public Matricula saveMatricula(MatriculaRequest matriculaRequest, Aluno aluno) {

         return matriculaRepository.save(
                new Matricula(aluno, matriculaRequest.descricaoSaude(),
                        matriculaRequest.dataMatricula(),
                        matriculaRequest.valorMatricula(),
                        matriculaRequest.status(),
                        matriculaRequest.motivoDesligamento(),
                        matriculaRequest.dataDesligamento())
        );
    }
}
