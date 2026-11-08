package org.pires.springstudy.dtos.request;

import jakarta.validation.constraints.*;

public record MatriculaAlunoDadosRequest(@NotNull String descricaoSaude,
                                         @Pattern(regexp = "^\\d{2}\\s?\\d{9}$",
                                                 message = "Formato de telefone inválido") @NotEmpty String telefone,
                                         @NotBlank @Email String email) {
}
