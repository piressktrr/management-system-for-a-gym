package org.pires.springstudy.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.pires.springstudy.infrastructure.entity.enums.Estado;

public record EnderecoRequest (String rua, Integer numero, @NotNull String complemento, String bairro,
                               String municipio, Estado estado, String pais, @Pattern(regexp = "^\\d{5}-?\\d{3}$") String cep) {
}
// essa pattern ai é pra validar o formato do cep
// colocar validaçao de not null e not blank em todos aqui