package org.pires.springstudy.service;

import org.pires.springstudy.dtos.RegisterRequest;
import org.pires.springstudy.dtos.response.AlunoResponse;
import org.pires.springstudy.dtos.response.ContratoResponse;
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


        var aluno = alunoService.saveAluno(registerRequest.aluno()); // retorno um Aluno invés de um AlunoResponse, para passar ele no saveMatricula
        var matricula = matriculaService.saveMatricula(registerRequest.matricula(), aluno);
        var contrato = contratoService.saveContrato(registerRequest.contrato(), matricula);
        // var matricula_plano = matriculaPlanoService.saveMatriculaPlano();


        return new RegisterResponse(
                new AlunoResponse(
                        aluno.getNome(),
                        aluno.getSobrenome(),
                        aluno.getSexo()),

                new MatriculaResponse(
                        matricula.getDataMatricula(),
                        matricula.getValorMatricula(),
                        matricula.getStatus()),

                new ContratoResponse(
                        contrato.getDataAssinatura()
                )
        );
    }
}
