package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matricula")
public class Matricula {
    public Matricula(Aluno aluno, String descricaoSaude,
                     LocalDateTime dataMatricula, BigDecimal valorMatricula,
                     StatusMatricula status, String motivoDesligamento, LocalDateTime dataDesligamento) {
        this.aluno = aluno;
        this.descricaoSaude = descricaoSaude;
        this.dataMatricula = dataMatricula;
        this.valorMatricula = valorMatricula;
        this.status = status;
        this.motivoDesligamento = motivoDesligamento;
        this.dataDesligamento = dataDesligamento;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_aluno")
    private Aluno aluno;

    @Column(length = 1000, nullable = false, columnDefinition = "text")
    private String descricaoSaude;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataMatricula;

    @Column(nullable = false, length = 10, precision = 5, scale = 2)
    private BigDecimal valorMatricula;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private StatusMatricula status;

    @Column(length = 100)
    private String motivoDesligamento;

    @Column()
    private LocalDateTime dataDesligamento;



}
