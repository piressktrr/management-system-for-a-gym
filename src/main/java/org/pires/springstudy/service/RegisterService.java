package org.pires.springstudy.service;

import jakarta.transaction.Transactional;
import org.pires.springstudy.dtos.request.alunoRegister.RegisterRequest;
import org.pires.springstudy.dtos.response.alunoRegister.*;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final ContratoService contratoService;
    private final MatriculaPlanoService matriculaPlanoService;
    private final PlanoService planoService;
    private final CobrancaService cobrancaService;

    public RegisterService(MatriculaService matriculaService, AlunoService alunoService,
                           ContratoService contratoService, MatriculaPlanoService matriculaPlanoService,
                           PlanoService planoService, CobrancaService cobrancaService) {
        this.matriculaService = matriculaService;
        this.alunoService = alunoService;
        this.contratoService = contratoService;
        this.matriculaPlanoService = matriculaPlanoService;
        this.planoService = planoService;
        this.cobrancaService = cobrancaService;
    }

    @Transactional
    public RegisterResponse saveTransactionalRegister(RegisterRequest registerRequest) {

        var aluno = alunoService.saveAluno(registerRequest.aluno()); // retorno um Aluno invés de um AlunoResponse, para passar ele no saveMatricula
        var matricula = matriculaService.saveMatricula(registerRequest.matricula(), aluno);
        var contrato = contratoService.saveContrato(registerRequest.contrato(), matricula);
        var plano = planoService.findById(registerRequest.planoId().id()); // pego o id do plano passado no json e retorno o do catalogo

        var matricula_plano = matriculaPlanoService.saveMatriculaPlano(registerRequest.matriculaPlano(),
                plano, matricula);

        var cobranca = cobrancaService.saveCobranca(registerRequest.cobranca(), matricula_plano);


        return new RegisterResponse(
                new AlunoResponse(
                        aluno.getNome(),
                        aluno.getSobrenome(),
                        aluno.getSexo()),

                new MatriculaResponse(
                        matricula.getId(),
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
                ),
                new CobrancaResponse(
                        cobranca.getId(),
                        cobranca.getStatusCobranca()
                )
        );
    }
}
