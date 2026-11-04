package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import org.pires.springstudy.dtos.RegisterRequest;
import org.pires.springstudy.dtos.request.*;
import org.pires.springstudy.dtos.response.AlunoResponse;
import org.pires.springstudy.dtos.response.RegisterResponse;
import org.pires.springstudy.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping("/register")
    public ResponseEntity<AlunoResponse> saveAluno(@Valid @RequestBody AlunoRequest alunoRequest) {

        return ResponseEntity.ok(alunoService.saveAluno(alunoRequest));

    }

//    @GetMapping("/search")
//    public ResponseEntity<List<AlunoResponse>> searchByNumComplCEP(@Valid @RequestParam Integer numero, String complemento, String cep) {
//
//        return ResponseEntity.ok(alunoService.searchByNumComplCep(numero, complemento, cep));
//
//    }


//    @PostMapping("/alunos/{id}/matriculas") -> rematriculas para um aluno ja existente,
//    chama o mesmo service de matricular do register

//    public ResponseEntity<AlunoResponse> saveMatriculaAndContrato() {
//
//    }
}
