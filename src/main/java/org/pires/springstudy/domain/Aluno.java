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
    private UUID idAluno;

    @ManyToOne(optional = false) // endereço tem varios alunos
    @JoinColumn(name = "endereco_id", nullable = false)

    private Endereco endereco;


    @Column(unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String cpfAluno;

    @Column(nullable = false)
    private String nomeAluno;

    @Column(nullable = false)
    private String sobrenomeAluno;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String sexo;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String telefoneAluno;

    @Column(nullable = false, unique = true)
    private String emailAluno;


}
