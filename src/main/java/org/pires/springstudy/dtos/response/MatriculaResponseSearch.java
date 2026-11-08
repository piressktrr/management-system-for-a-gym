package org.pires.springstudy.dtos.response;

import org.pires.springstudy.dtos.request.alunoRegister.AlunoRequest;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.time.LocalDateTime;
import java.util.UUID;

public record MatriculaResponseSearch(UUID id, Aluno aluno, LocalDateTime dataMatricula, StatusMatricula status) {
}
