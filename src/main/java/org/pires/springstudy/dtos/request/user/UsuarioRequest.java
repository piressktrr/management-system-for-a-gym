package org.pires.springstudy.dtos.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(String nome, String sobrenome, @NotBlank @NotNull String identificadorLogin, @NotBlank @NotNull String senha) {
}
