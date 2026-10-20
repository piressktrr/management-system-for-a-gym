package org.pires.springstudy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID matriculaId;

    @ManyToOne(optional = false)
    @JoinColumn(unique = true, nullable = false, name = "alunoId")
    private Aluno aluno;

    private String descricaoSaude;

    @Column(nullable = false)
    private LocalDateTime dataMatricula;

    @Column(nullable = false)
    private BigDecimal valorMatricula;
    private String motivoDesligamento;
    private String status;

}
