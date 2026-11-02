package org.pires.springstudy.dtos.response;

import org.pires.springstudy.infrastructure.entity.Sexo;

public record AlunoResponse(String nome, String sobrenome, Sexo sexo) {
}
