package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.Estado;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
        // seria melhor eu gerar um Long para o ID para salvar o endereço? caso tenha varios alunos com o mesmo endereço
        // pra ficar mais facil de consultar
    public Endereco(String rua, Integer numero, String complemento, String bairro, String municipio, Estado estado, String pais, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 16)
    private Integer numero;

    @Column(length = 50)
    private String complemento;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false, length = 8)
    private String cep;



}
