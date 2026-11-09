package org.pires.springstudy.dtos.request.alunoRegister;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.pires.springstudy.dtos.PlanoRequest;

public record RegisterRequest(@Valid @NotNull AlunoRequest aluno,
                              @Valid @NotNull MatriculaRequest matricula,
                              @Valid @NotNull ContratoRequest contrato,
                              @NotNull PlanoRequest planoId,
                              @Valid @NotNull MatriculaPlanoRequest matriculaPlano,
                              @Valid @NotNull CobrancaRequest cobranca) {
}
