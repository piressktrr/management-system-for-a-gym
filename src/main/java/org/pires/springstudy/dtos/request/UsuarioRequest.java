package org.pires.springstudy.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(String nome, String sobrenome, @NotBlank String identificadorLogin, @NotBlank String senha) {
}
