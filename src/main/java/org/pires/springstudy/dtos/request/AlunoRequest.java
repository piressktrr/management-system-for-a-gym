package org.pires.springstudy.dtos;

import org.pires.springstudy.infrastructure.entity.Endereco;
import org.pires.springstudy.infrastructure.entity.Sexo;

import java.time.LocalDate;

public record AlunoRequest (Endereco endereco, String CPF, String nome, String sobrenome, Sexo sexo,
                            LocalDate dataNascimento, String telefone, String email){
}
