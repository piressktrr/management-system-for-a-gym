package org.pires.springstudy.dtos;

import jakarta.validation.constraints.NotNull;
import org.pires.springstudy.dtos.request.AlunoRequest;
import org.pires.springstudy.dtos.request.MatriculaRequest;

public record RegisterRequest(@NotNull AlunoRequest alunoRequest, @NotNull MatriculaRequest matriculaRequest) {
}
