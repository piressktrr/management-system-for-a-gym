package org.pires.springstudy.service;

import org.pires.springstudy.dtos.RegisterRequest;
import org.pires.springstudy.dtos.response.AlunoResponse;
import org.pires.springstudy.dtos.response.MatriculaResponse;
import org.pires.springstudy.dtos.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final ContratoService contratoService;

    public RegisterService(MatriculaService matriculaService, AlunoService alunoService, ContratoService contratoService) {
        this.matriculaService = matriculaService;
        this.alunoService = alunoService;
        this.contratoService = contratoService;
    }

    public RegisterResponse saveTransactionalRegister(RegisterRequest registerRequest) {


        var a = alunoService.saveAluno(registerRequest.alunoRequest()); // retorno um Aluno invés de um AlunoResponse, para passar ele no saveMatricula
        var b = matriculaService.saveMatricula(registerRequest.matriculaRequest(), a);
//        var c = contratoService.saveContrato(registerRequest.contratoRequest(), b);


        return new RegisterResponse(
                new AlunoResponse(
                        a.getNome(),
                        a.getSobrenome(),
                        a.getSexo()),

                new MatriculaResponse(
                        b.getDataMatricula(),
                        b.getValorMatricula(),
                        b.getStatus())
        );
    }
}
