package org.pires.springstudy.dtos.request.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.TipoCobranca;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CobrancaRequest(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                              LocalDateTime dataDeVencimento, BigDecimal valorAPagar, TipoCobranca tipoCobranca,
                              StatusCobranca statusCobranca) {
}
