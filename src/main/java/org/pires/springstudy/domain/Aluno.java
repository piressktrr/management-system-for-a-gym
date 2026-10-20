package org.pires.springstudy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private UUID id_aluno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "endereco_id")
    private Endereco endereco;


    @Column(unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String cpf_aluno;

    @Column(nullable = false)
    private String nome_aluno;

    @Column(nullable = false)
    private String sobrenome_aluno;

    @Column(nullable = false)
    private Character sexo;

    @Column(nullable = false)
    private LocalDate data_nascimento;

    @Column(nullable = false, unique = true)
    private String telefone_aluno;

    @Column(nullable = false, unique = true)
    private String email_aluno;


}
