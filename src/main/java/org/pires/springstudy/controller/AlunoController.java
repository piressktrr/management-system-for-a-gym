package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.pires.springstudy.dtos.request.alunoRegister.RegisterRequest;
import org.pires.springstudy.dtos.response.MatriculaResponseSearch;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.MatriculaResponse;
import org.pires.springstudy.dtos.response.alunoRegister.RegisterResponse;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.service.AlunoService;
import org.pires.springstudy.service.MatriculaService;
import org.pires.springstudy.service.PlanoService;
import org.pires.springstudy.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final RegisterService registerService; // injeçao por atributo só para teste

    private final AlunoService alunoService;

    private final MatriculaService matriculaService;

    public AlunoController(RegisterService registerService, AlunoService alunoService, MatriculaService matriculaService) {
        this.registerService = registerService;
        this.alunoService = alunoService;
        this.matriculaService = matriculaService;
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

    @GetMapping("/search/cpf")
    public ResponseEntity<AlunoResponseSearch> searchAlunoByCpf(@RequestParam("cpf") @Valid @CPF String cpf) {

        return new ResponseEntity<>(alunoService.searchAlunoByCPF(cpf), HttpStatus.OK);
    }


    @GetMapping("/search/status")
    // busca alunos por status da cobranca e por cobranca vencida = true ou false (se for true ignora os status da cobranca)
    public ResponseEntity<List<AlunoResponse>> searchAlunosByStatusCobranca(
            // name define o nome do parametro a ser bindado
            @RequestParam(name = "statusCobranca", required = false) StatusCobranca statusCobranca,
            @RequestParam(name = "cobrancaVencida", defaultValue = "false", required = false) boolean cobrancaVencida) {

        if (!cobrancaVencida) {
            return new ResponseEntity<>(alunoService.searchAlunoByStatusCobranca(statusCobranca), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(alunoService.searchAlunoByCobrancaVencida(), HttpStatus.OK);
        }

    }

    @GetMapping("/{alunoId}/matriculas")
    public ResponseEntity<List<MatriculaResponseSearch>> searchAllMatriculasByAluno(@PathVariable UUID alunoId) {

        return new ResponseEntity<>(matriculaService.searchAllMatriculasByAluno(alunoId), HttpStatus.OK);
    }







//    @PostMapping("/alunos/{id}/matriculas") -> rematriculas para um aluno ja existente,
//    chama o mesmo service de matricular do register

//    public ResponseEntity<AlunoResponse> saveMatriculaAndContrato() {
//
//    }

}
