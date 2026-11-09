package org.pires.springstudy.service;

import org.pires.springstudy.dtos.PlanoResponseSearch;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.PlanoResponse;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
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


    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<PlanoResponseSearch> getPlanos() {

        var w = planoRepository.findAll();

        return w.stream().map(p -> new PlanoResponseSearch(
                p.getId(), p.getTipoPlano(), p.getValorPlano(), p.getDuracaoMeses(), p.isPermitePessoaExtra())).toList();

    }

    public Plano findById(Long id) {
        return planoRepository.findById(id).orElseThrow(() -> new PlanoNotFound("Plano não encontrado na base da dados!"));
    }


}
