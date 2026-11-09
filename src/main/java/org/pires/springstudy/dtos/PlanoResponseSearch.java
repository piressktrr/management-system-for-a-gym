package org.pires.springstudy.dtos;

import org.pires.springstudy.infrastructure.entity.enums.TipoPlano;

import java.math.BigDecimal;

public record PlanoResponseSearch(Long id, TipoPlano tipoPlano, BigDecimal valorPlano, Integer duracaoMeses, boolean pessoaExtra) {
}
