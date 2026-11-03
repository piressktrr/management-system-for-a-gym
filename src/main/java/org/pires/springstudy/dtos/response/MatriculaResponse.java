package org.pires.springstudy.dtos.response;

import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MatriculaResponse(LocalDateTime dataMatricula, BigDecimal valorMatricula, StatusMatricula status) {
}
