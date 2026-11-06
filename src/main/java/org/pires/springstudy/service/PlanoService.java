package org.pires.springstudy.service;

import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.infrastructure.exceptions.PlanoNotFound;
import org.pires.springstudy.infrastructure.repository.AlunoRepository;
import org.pires.springstudy.infrastructure.repository.MatriculaPlanoRepository;
import org.pires.springstudy.infrastructure.repository.MatriculaRepository;
import org.pires.springstudy.infrastructure.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlanoService {
    private final PlanoRepository planoRepository;
    @Autowired
    private MatriculaPlanoRepository matriculaPlanoRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> getPlanos() {

        return planoRepository.findAll();
    }

    public Plano findById(Long id) {
        return planoRepository.findById(id).orElseThrow(() -> new PlanoNotFound("Plano não encontrado na base da dados!"));
    }

    public List<AlunoResponse> findAlunosByPlanoId(Long planoId) {
        List<UUID> matriculasPlanos = matriculaPlanoRepository.findMatriculaPlanoByPlano(findById(planoId))
                .stream()
                .map(m -> m.getMatricula().getId())
                .toList();

        List<Matricula> matriculas  = matriculaRepository.findAllById(matriculasPlanos);

        List<Aluno> alunos =  alunoRepository.findAllById(matriculas
                .stream()
                .map(m -> m.getAluno().getId())
                .toList());

        return alunos
                .stream()
                .map(a -> new AlunoResponse(a.getNome(), a.getSobrenome(), a.getSexo()))
                .toList();
    }


}
