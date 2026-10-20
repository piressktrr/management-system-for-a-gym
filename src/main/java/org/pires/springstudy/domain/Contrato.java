package org.pires.springstudy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID contratoId;

    @OneToOne
    @JoinColumn(nullable = false, name = "matricula_id")
    private Matricula matricula;

    @Column(nullable = false)
    private String descricaoContratual;

    @Column(nullable = false)
    private LocalDateTime dataAssinatura;

    @Column(nullable = false)
    private String assinaturaEscrita;
}
