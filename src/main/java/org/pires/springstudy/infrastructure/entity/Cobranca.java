package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.TipoCobranca;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cobranca")
public class Cobranca {

    public Cobranca(Matricula matricula, MatriculaPlano matriculaPlano, LocalDate dataDeVencimento, BigDecimal valorAPagar, TipoCobranca tipoCobranca, StatusCobranca statusCobranca) {
        this.matricula = matricula;
        this.matriculaPlano = matriculaPlano;
        this.dataDeVencimento = dataDeVencimento;
        this.valorAPagar = valorAPagar;
        this.tipoCobranca = tipoCobranca;
        this.statusCobranca = statusCobranca;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_matricula", unique = false)
    private Matricula matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matricula_plano")
    private MatriculaPlano matriculaPlano;


    @Column(nullable = false, name = "data_de_vencimento_cobranca")
    private LocalDate dataDeVencimento;

    @Column(nullable = false, precision = 10, scale = 2, name = "valor_a_pagar")
    private BigDecimal valorAPagar;

    @Column(nullable = false, name = "tipo_de_cobranca", length = 30)
    @Enumerated(EnumType.STRING)
    private TipoCobranca tipoCobranca;

    @Column(nullable = false, name = "status_da_cobranca", length = 30)
    @Enumerated(EnumType.STRING)
    private StatusCobranca statusCobranca;
}
