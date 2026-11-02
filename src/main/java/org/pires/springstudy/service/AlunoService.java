package org.pires.springstudy.service;

import org.pires.springstudy.dtos.AlunoRequest;
import org.pires.springstudy.dtos.AlunoResponse;
import org.pires.springstudy.infrastructure.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse saveAluno(@RequestBody AlunoRequest alunoRequest) {}

    public AlunoResponse atualizarAluno(@RequestBody AlunoRequest alunoRequest) {}

    public AlunoResponse desligarAluno(@RequestBody AlunoRequest alunoRequest) {}
}
