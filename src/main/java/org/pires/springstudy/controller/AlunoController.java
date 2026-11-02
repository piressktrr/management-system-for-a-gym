package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import org.pires.springstudy.dtos.request.AlunoRequest;
import org.pires.springstudy.dtos.response.AlunoResponse;
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
    public ResponseEntity<AlunoResponse> saveAluno(@Valid @RequestBody AlunoRequest alunoRequest) {

        return ResponseEntity.ok(alunoService.saveAluno(alunoRequest));

    }
}
