package org.pires.springstudy.dtos.request.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ContratoRequest(String descricaoContratual,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                              LocalDateTime dataAssinatura,
                              String assinatura) {
}
