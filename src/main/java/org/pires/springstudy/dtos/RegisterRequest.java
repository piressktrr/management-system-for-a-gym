package org.pires.springstudy.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.pires.springstudy.dtos.request.AlunoRequest;
import org.pires.springstudy.dtos.request.ContratoRequest;
import org.pires.springstudy.dtos.request.MatriculaRequest;

public record RegisterRequest(@Valid AlunoRequest aluno,
                              @Valid MatriculaRequest matricula,
                              @Valid ContratoRequest contrato) {
}
