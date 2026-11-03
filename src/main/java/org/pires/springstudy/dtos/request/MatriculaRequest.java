package org.pires.springstudy.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.pires.springstudy.infrastructure.entity.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MatriculaRequest (String descricao, @JsonFormat(pattern = "dd/MM/yyyy") LocalDateTime dataMatricula,
                                BigDecimal valorMatricula, StatusMatricula status, String motivoDesligamento,
                                LocalDateTime dataDesligamento) {
}
