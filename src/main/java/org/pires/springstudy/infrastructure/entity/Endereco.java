package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

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
    private String estado;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false, length = 75)
    private String cep;



}
