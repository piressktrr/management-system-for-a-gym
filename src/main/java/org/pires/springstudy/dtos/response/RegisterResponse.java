package org.pires.springstudy.dtos.response;

import org.pires.springstudy.dtos.request.AlunoRequest;

public record RegisterResponse(AlunoResponse aluno, MatriculaResponse matricula) {
}
