package org.pires.springstudy.domain;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Aluno {

    private UUID ID_Aluno;
    private UUID ID_Matricula;
    private String CPF_Aluno;
    private String Nome_Aluno;
    private String Sobrenome_Aluno;
    private LocalDateTime Data_Nascimento_Aluno;
    private String Telefone_Aluno;
    private String Email_Aluno;
    private String Sexo_Aluno;
    private String Genero_Aluno;

}
