package org.pires.springstudy.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank @Email(message = "Formato de Email não é valido") String identificadorLogin,
                           @NotBlank String senha) {
}
