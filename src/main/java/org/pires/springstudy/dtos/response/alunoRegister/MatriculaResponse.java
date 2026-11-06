package org.pires.springstudy.dtos.response.alunoRegister;

import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MatriculaResponse(UUID id , LocalDateTime dataMatricula, BigDecimal valorMatricula, StatusMatricula status) {
}
