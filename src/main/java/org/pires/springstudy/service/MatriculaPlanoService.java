package org.pires.springstudy.service;

import org.pires.springstudy.infrastructure.repository.MatriculaPlanoRepository;
import org.springframework.stereotype.Service;

@Service
public class MatriculaPlanoService {

    private final MatriculaPlanoRepository matriculaPlanoRepository;

    public MatriculaPlanoService(MatriculaPlanoRepository matriculaPlanoRepository) {
        this.matriculaPlanoRepository = matriculaPlanoRepository;
    }

//    public MatriculaPlano saveMatriculaPlano(MatriculaPlanoRequest matriculaPlano, Plano plano, Matricula matricula ) {
//        return matriculaPlanoRepository.save(new MatriculaPlano(
//                matricula,
//                planoId,
//                matriculaPlano.dataInicio(),
//                matriculaPlano.dataFim()
//        ));
//    }
}
