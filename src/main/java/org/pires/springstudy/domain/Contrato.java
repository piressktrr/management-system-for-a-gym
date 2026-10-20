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
    private Matricula matricula;

    private String descricaoContratual;

    private LocalDateTime dataAssinatura;

    private String assinaturaEscrita;
}
