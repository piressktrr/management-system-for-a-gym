package org.pires.springstudy.dtos.response.alunoRegister;

import org.pires.springstudy.infrastructure.entity.enums.Sexo;

public record AlunoResponse(String nome, String sobrenome, Sexo sexo) {
}
