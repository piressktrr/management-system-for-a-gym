package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.TipoPlano;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano; // vou expor isso aqui pra matricula associar o id do plano

    @Column(nullable = false, length = 15, precision = 5, scale = 2)
    private BigDecimal valorPlano;

    @Column(nullable = false, length = 18)
    private Integer duracaoMeses;

    @Column(nullable = false, length = 10) // sim ou nao
    private boolean pessoaExtra;
}
