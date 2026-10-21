package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "id_aluno")
    private Aluno aluno;

    @Column(length = 100)
    private String descricaoSaude;

    @Column(nullable = false, length = 20)
    private LocalDateTime dataMatricula;

    @Column(nullable = false, length = 10, precision = 2, scale = 2)
    private BigDecimal valorMatricula;

    @Column(length = 100)
    private String motivoDesligamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String status;

}
