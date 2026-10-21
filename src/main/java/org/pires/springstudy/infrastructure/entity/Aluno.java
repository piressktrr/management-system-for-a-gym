package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(optional = false) // endereço tem varios alunos
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;


    @Column(unique = true, length = 11)
    private String CPF;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private String sexo;

    @Column(nullable = false, name = "data_nascimento", length = 10)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 11) // ddd + numero nesse caso, pais ja vem automatico
    private String telefone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;


}
