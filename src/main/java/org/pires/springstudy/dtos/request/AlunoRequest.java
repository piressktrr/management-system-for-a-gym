package org.pires.springstudy.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.pires.springstudy.infrastructure.entity.Endereco;
import org.pires.springstudy.infrastructure.entity.Sexo;

import java.time.LocalDate;

public record AlunoRequest (EnderecoRequest endereco,
                            @CPF(message = "Cpf inválido, digite um válido para prosseguir") String CPF,
                            String nome,
                            String sobrenome,
                            Sexo sexo,
                            String complemento,
                            @JsonFormat(pattern = "dd/MM/yyyy") @Past(message = "A data deve ser no passado!")
                            LocalDate dataNascimento,
                            @Pattern(regexp = "^\\d{2}\\s?\\d{9}$",
                                    message = "Formato de telefone inválido") String telefone,
                            @Email(message = "Email not valid, write right") String email){
}

// @cpf valida o cpf obivmanete
// @jsonformat faz a data ficar como dia mes ano
// @past só permite que a data seja no passado de agora
// @pattern é o regex para validar telefone
