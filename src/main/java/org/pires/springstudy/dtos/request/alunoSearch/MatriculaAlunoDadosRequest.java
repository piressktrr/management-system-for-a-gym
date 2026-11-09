package org.pires.springstudy.dtos.request.alunoSearch;

import jakarta.validation.constraints.*;

public record MatriculaAlunoDadosRequest(@NotNull String descricaoSaude,
                                         @Pattern(regexp = "^\\d{2}\\s?\\d{9}$",
                                                 message = "Formato de telefone inválido") String telefone,
                                         @Email String email) {
}
