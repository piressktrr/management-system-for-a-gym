package org.pires.springstudy.dtos.response.alunoRegister;

import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;

import java.util.UUID;

public record CobrancaResponse(UUID id, StatusCobranca statusCobranca) {
}
