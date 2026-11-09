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
@Setter // deixei o setter pq é mais facil colocar updatable false do que ao contrario
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    public Contrato(Matricula matricula, String descricaoContratual, LocalDateTime dataAssinatura, String assinatura) {
        this.matricula = matricula;
        this.descricaoContratual = descricaoContratual;
        this.dataAssinatura = dataAssinatura;
        this.assinatura = assinatura;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_matricula", updatable = false)
    private Matricula matricula;

    @Column(nullable = false, updatable = false)
    private String descricaoContratual;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataAssinatura;

    @Column(nullable = false, updatable = false)
    private String assinatura;

    @Column
    private LocalDateTime dataEncerramento;
}
