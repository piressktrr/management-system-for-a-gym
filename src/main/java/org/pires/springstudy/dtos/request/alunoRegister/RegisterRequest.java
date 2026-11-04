package org.pires.springstudy.dtos.request.alunoRegister;

import jakarta.validation.Valid;
import org.pires.springstudy.dtos.PlanoRequest;

public record RegisterRequest(@Valid AlunoRequest aluno,
                              @Valid MatriculaRequest matricula,
                              @Valid ContratoRequest contrato,
                              PlanoRequest plano,
                              @Valid MatriculaPlanoRequest matriculaPlano,
                              @Valid CobrancaRequest cobranca) {
}
