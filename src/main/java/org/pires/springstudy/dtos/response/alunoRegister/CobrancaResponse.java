package org.pires.springstudy.dtos.response.alunoRegister;

import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;

public record CobrancaResponse(StatusCobranca statusCobranca) {
}
