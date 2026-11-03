package org.pires.springstudy.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotBlank @NotNull @Email(message = "Formato de Email não é valido") String identificadorLogin,
                           @NotBlank @NotNull String senha) {
}
