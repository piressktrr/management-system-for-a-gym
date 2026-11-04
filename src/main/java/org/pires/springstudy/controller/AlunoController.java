package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import org.pires.springstudy.dtos.RegisterRequest;
import org.pires.springstudy.dtos.request.*;
import org.pires.springstudy.dtos.response.AlunoResponse;
import org.pires.springstudy.dtos.response.RegisterResponse;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.service.AlunoService;
import org.pires.springstudy.service.PlanoService;
import org.pires.springstudy.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final RegisterService registerService; // injeçao por atributo só para teste

    private final PlanoService planoService;

    public AlunoController(RegisterService registerService, PlanoService planoService) {
        this.registerService = registerService;
        this.planoService = planoService;
    }

    //    @PostMapping("/register") // o atual de como ta agora, o de baixo é pra teste
//    public ResponseEntity<AlunoResponse> saveAluno(@Valid @RequestBody AlunoRequest alunoRequest) {
//
//        return ResponseEntity.ok(alunoService.saveAluno(alunoRequest));
//
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> saveTest(@Valid @RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(registerService.saveTransactionalRegister(registerRequest));

    }

    @GetMapping("/planos")
    public ResponseEntity<List<Plano>> getPlanos() {
        return ResponseEntity.ok(planoService.getPlanos());
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
