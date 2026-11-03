package org.pires.springstudy.dtos.request;

import org.pires.springstudy.infrastructure.entity.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MatriculaRequest (AlunoRequest aluno, String descricao, LocalDateTime dataMatricula,
                                BigDecimal valorMatricula, StatusMatricula status, String motivoDesligamento,
                                LocalDateTime dataDesligamento) {
}
