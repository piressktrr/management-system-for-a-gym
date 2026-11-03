package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.entity.enums.Tipo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cobrança")
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_matricula_plano")
    private MatriculaPlano idMatricula;


    @Column(nullable = false, name = "Data_De_Vencimento_Cobranca")
    private LocalDateTime dataDeVencimento;

    @Column(nullable = false, length = 15, precision = 5, scale = 2, name = "Valor_A_Pagar")
    private BigDecimal valorAPagar;

    @Column(nullable = false, name = "Tipo_De_Cobranca")
    @Enumerated(EnumType.STRING)
    private Tipo tipoCobranca;

    @Column(nullable = false, name = "Status_Da_Cobranca")
    @Enumerated(EnumType.STRING)
    private StatusCobranca statusCobranca;
}
