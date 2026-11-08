package org.pires.springstudy.dtos.response.alunoSearch;

import org.pires.springstudy.infrastructure.entity.enums.Sexo;

import java.util.UUID;

public record AlunoResponseSearch(UUID id, String nome, String sobrenome, Sexo sexo) {
}
