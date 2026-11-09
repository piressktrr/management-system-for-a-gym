package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.TipoCobranca;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cobranca")
public class Cobranca {

    public Cobranca(MatriculaPlano idMatriculaPlano, LocalDateTime dataDeVencimento, BigDecimal valorAPagar, TipoCobranca tipoCobranca, StatusCobranca statusCobranca) {
        this.matriculaPlano = idMatriculaPlano;
        this.dataDeVencimento = dataDeVencimento;
        this.valorAPagar = valorAPagar;
        this.tipoCobranca = tipoCobranca;
        this.statusCobranca = statusCobranca;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "matriculaPlano")
    private MatriculaPlano matriculaPlano;


    @Column(nullable = false, name = "data_de_vencimento_cobranca")
    private LocalDateTime dataDeVencimento;

    @Column(nullable = false, length = 15, precision = 5, scale = 2, name = "Valor_A_Pagar")
    private BigDecimal valorAPagar;

    @Column(nullable = false, name = "tipo_de_cobranca", length = 30)
    @Enumerated(EnumType.STRING)
    private TipoCobranca tipoCobranca;

    @Column(nullable = false, name = "status_da_cobranca", length = 30)
    @Enumerated(EnumType.STRING)
    private StatusCobranca statusCobranca;
}
