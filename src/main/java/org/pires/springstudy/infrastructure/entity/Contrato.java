package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    public Contrato(Matricula matricula, String descricaoContratual, LocalDateTime dataAssinatura, String assinaturaEscrita) {
        this.matricula = matricula;
        this.descricaoContratual = descricaoContratual;
        this.dataAssinatura = dataAssinatura;
        this.assinaturaEscrita = assinaturaEscrita;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

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
