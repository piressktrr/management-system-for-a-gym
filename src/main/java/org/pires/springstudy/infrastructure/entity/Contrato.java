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
@Table(name = "contrato")
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
    @JoinColumn(nullable = false, name = "id_matricula", updatable = false, unique = true)
    private Matricula matricula;

    @Column(nullable = false, updatable = false, columnDefinition = "text")
    private String descricaoContratual;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataAssinatura;

    @Column(nullable = false, updatable = false)
    private String assinatura; // nome de alguem que assinou

    @Column
    private LocalDateTime dataEncerramento; // fazer ser calculado a partir da matricula

}
