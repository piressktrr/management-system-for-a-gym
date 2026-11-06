package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import org.pires.springstudy.dtos.request.alunoRegister.RegisterRequest;
import org.pires.springstudy.dtos.response.alunoRegister.RegisterResponse;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.service.PlanoService;
import org.pires.springstudy.service.RegisterService;
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


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> saveTest(@Valid @RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(registerService.saveTransactionalRegister(registerRequest),
                HttpStatus.CREATED);


    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/planos")
    public ResponseEntity<List<Plano>> getPlanos() {
        return new ResponseEntity<>(planoService.getPlanos(), HttpStatus.FOUND);

    }

//    @PostMapping("/alunos/{id}/matriculas") -> rematriculas para um aluno ja existente,
//    chama o mesmo service de matricular do register

//    public ResponseEntity<AlunoResponse> saveMatriculaAndContrato() {
//
//    }
}
