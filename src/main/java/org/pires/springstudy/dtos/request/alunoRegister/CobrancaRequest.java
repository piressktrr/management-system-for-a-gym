package org.pires.springstudy.dtos.request.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.TipoCobranca;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CobrancaRequest(TipoCobranca tipoCobranca) {
}
