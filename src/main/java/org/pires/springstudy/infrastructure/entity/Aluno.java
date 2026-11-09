package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.Sexo;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno {

    public Aluno(Endereco endereco, String CPF, String nome, String sobrenome, Sexo sexo, LocalDate dataNascimento, String telefone, String email) {
        this.endereco = endereco;
        this.cpf = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) // endereço tem varios alunos
    // o cascade type me permite salvar aluno com endereço e ja levar pro banco ambas as entidades
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;


    @Column(unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING) // ja converte automaticamente persistindo a string no banco
    private Sexo sexo;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 11) // ddd + numero nesse caso, pais ja vem automatico
    private String telefone;

    @Column(nullable = false, unique = true, length = 254)
    private String email;



}
