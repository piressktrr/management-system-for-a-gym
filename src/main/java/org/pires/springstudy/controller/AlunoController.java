package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import org.pires.springstudy.dtos.request.alunoRegister.RegisterRequest;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.RegisterResponse;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.service.AlunoService;
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

    private final AlunoService alunoService;


    public AlunoController(RegisterService registerService, PlanoService planoService, AlunoService alunoService) {
        this.registerService = registerService;
        this.planoService = planoService;
        this.alunoService = alunoService;
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> saveRegisterAluno(@Valid @RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(registerService.saveTransactionalRegister(registerRequest),
                HttpStatus.CREATED);


    }

    @GetMapping("/search/nome")
    public ResponseEntity<List<AlunoResponseSearch>> searchAlunosByName(@RequestParam("nome") String nome) {

        return new ResponseEntity<>(alunoService.searchAlunoByName(nome), HttpStatus.OK);
    }


    @GetMapping("/search/status") // busca alunos por status da cobranca
    public ResponseEntity<List<AlunoResponse>> searchAlunosByStatusCobranca(
            @RequestParam(defaultValue = "statusCobranca", required = false) StatusCobranca statusCobranca,
            @RequestParam(defaultValue = "cobrancaVencida", required = false) boolean cobrancaVencida) {
        if (!cobrancaVencida) {
            return new ResponseEntity<>(alunoService.searchAlunoByStatusCobranca(statusCobranca), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(alunoService.searchAlunoByCobrancaVencida(), HttpStatus.OK);
        }

    }
    //@GetMapping("/search/vencidos")

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/planos")
    public ResponseEntity<List<Plano>> getPlanos() {
        return new ResponseEntity<>(planoService.getPlanos(), HttpStatus.OK);

    }

//    @PostMapping("/alunos/{id}/matriculas") -> rematriculas para um aluno ja existente,
//    chama o mesmo service de matricular do register

//    public ResponseEntity<AlunoResponse> saveMatriculaAndContrato() {
//
//    }

}
