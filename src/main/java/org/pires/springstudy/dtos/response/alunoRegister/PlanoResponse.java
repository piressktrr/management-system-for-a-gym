package org.pires.springstudy.dtos.response.alunoRegister;

import org.pires.springstudy.infrastructure.entity.enums.TipoPlano;

import java.math.BigDecimal;

public record PlanoResponse(TipoPlano tipoPlano, BigDecimal valorPlano) {
}
