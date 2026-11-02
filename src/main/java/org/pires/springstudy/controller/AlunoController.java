package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.AlunoRequest;
import org.pires.springstudy.dtos.AlunoResponse;
import org.pires.springstudy.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/register")
    public ResponseEntity<AlunoResponse> saveAluno(@RequestBody AlunoRequest alunoRequest) {

        return ResponseEntity.ok(alunoService.saveAluno(alunoRequest));

    }
}
