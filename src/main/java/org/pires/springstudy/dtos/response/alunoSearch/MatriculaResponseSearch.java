package org.pires.springstudy.dtos.response.alunoSearch;

import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.time.LocalDateTime;
import java.util.UUID;

public record MatriculaResponseSearch(UUID idMatricula, AlunoResponseSearch aluno, LocalDateTime dataMatricula, StatusMatricula status) {
}
