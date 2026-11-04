package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.RegisterRequest;
import org.pires.springstudy.dtos.response.alunoRegister.*;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final ContratoService contratoService;
    private final MatriculaPlanoService matriculaPlanoService;
    private final PlanoService planoService;

    public RegisterService(MatriculaService matriculaService, AlunoService alunoService, ContratoService contratoService, MatriculaPlanoService matriculaPlanoService, PlanoService planoService) {
        this.matriculaService = matriculaService;
        this.alunoService = alunoService;
        this.contratoService = contratoService;
        this.matriculaPlanoService = matriculaPlanoService;
        this.planoService = planoService;
    }

    public RegisterResponse saveTransactionalRegister(RegisterRequest registerRequest) {

        var aluno = alunoService.saveAluno(registerRequest.aluno()); // retorno um Aluno invés de um AlunoResponse, para passar ele no saveMatricula
        var matricula = matriculaService.saveMatricula(registerRequest.matricula(), aluno);
        var contrato = contratoService.saveContrato(registerRequest.contrato(), matricula);
        var plano = planoService.findById(registerRequest.plano().id());

        var matricula_plano = matriculaPlanoService.saveMatriculaPlano(registerRequest.matriculaPlano(),
                plano, matricula);


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
                ),

                new PlanoResponse(
                        plano.getTipoPlano(), plano.getValorPlano()
                ),

                new MatriculaPlanoResponse(
                        matricula_plano.getDataInicio(), matricula_plano.getDataFim()
                )
        );
    }
}
