package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.RegisterRequest;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.ContratoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.MatriculaResponse;
import org.pires.springstudy.dtos.response.alunoRegister.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final ContratoService contratoService;
    private final MatriculaPlanoService matriculaPlanoService;

    public RegisterService(MatriculaService matriculaService, AlunoService alunoService, ContratoService contratoService, MatriculaPlanoService matriculaPlanoService) {
        this.matriculaService = matriculaService;
        this.alunoService = alunoService;
        this.contratoService = contratoService;
        this.matriculaPlanoService = matriculaPlanoService;
    }

    public RegisterResponse saveTransactionalRegister(RegisterRequest registerRequest) {


        var aluno = alunoService.saveAluno(registerRequest.aluno()); // retorno um Aluno invés de um AlunoResponse, para passar ele no saveMatricula
        var matricula = matriculaService.saveMatricula(registerRequest.matricula(), aluno);
        var contrato = contratoService.saveContrato(registerRequest.contrato(), matricula);
        var matricula_plano = matriculaPlanoService.saveMatriculaPlano(registerRequest.matriculaPlano(),
                registerRequest.plano(), matricula );


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
