package org.pires.springstudy.dtos.request;

import java.time.LocalDateTime;

public record ContratoRequest(String descricaoContratual, LocalDateTime dataAssinatura, String assinaturaEscrita) {
}
