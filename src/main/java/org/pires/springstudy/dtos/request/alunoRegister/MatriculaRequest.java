package org.pires.springstudy.dtos.request.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MatriculaRequest (String descricaoSaude,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                LocalDateTime dataMatricula,
                                BigDecimal valorMatricula, StatusMatricula status, String motivoDesligamento,
                                LocalDateTime dataDesligamento) {
}
