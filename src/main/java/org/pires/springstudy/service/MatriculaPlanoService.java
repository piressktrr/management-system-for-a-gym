package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.MatriculaPlanoRequest;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.infrastructure.repository.MatriculaPlanoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MatriculaPlanoService {

    private final MatriculaPlanoRepository matriculaPlanoRepository;

    public MatriculaPlanoService(MatriculaPlanoRepository matriculaPlanoRepository) {
        this.matriculaPlanoRepository = matriculaPlanoRepository;
    }

    public MatriculaPlano saveMatriculaPlano(MatriculaPlanoRequest matriculaPlano, Plano plano, Matricula matricula ) {
        return matriculaPlanoRepository.save(new MatriculaPlano(
                matricula,
                plano,
                matriculaPlano.dataInicio(), // isso vai derivar da data de inicio do plano
                null
        ));
    }
}
