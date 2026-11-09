package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.MatriculaPlanoRequest;
import org.pires.springstudy.dtos.response.alunoRegister.MatriculaResponse;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.infrastructure.repository.MatriculaPlanoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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


    public List<AlunoResponseSearch> findAlunosByPlanoId(Long planoId) {

        return matriculaPlanoRepository.findAlunosByPlanoId(planoId).stream()
                .map(a -> new AlunoResponseSearch(a.getId(), a.getNome(), a.getSobrenome(), a.getSexo()))
                .toList();

//        List<UUID> matriculasPlanos = matriculaPlanoRepository.findMatriculaPlanoByPlano(findById(planoId))
//                .stream()
//                .filter(m -> m.getDataFim() == null)
//                .map(m -> m.getMatricula().getId())
//                .toList();
//
//        List<Matricula> matriculas  = matriculaRepository.findAllById(matriculasPlanos);
//
//        List<Aluno> alunos =  alunoRepository.findAllById(matriculas
//                .stream()
//                .map(m -> m.getAluno().getId())
//                .toList());
//
//        return alunos
//                .stream()
//                .map(a -> new AlunoResponse(a.getNome(), a.getSobrenome(), a.getSexo()))
//                .toList();
    }
}
