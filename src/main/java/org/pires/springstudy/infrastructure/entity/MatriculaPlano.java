package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matricula_plano")
public class MatriculaPlano {

    public MatriculaPlano(Matricula matricula, Plano plano, LocalDate dataInicio, LocalDate dataFim) {
        this.matricula = matricula;
        this.plano = plano;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_matricula_plano")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_matricula")
    private Matricula matricula;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_plano")
    private Plano plano;

    @Column(length = 10, nullable = false, name = "Data_Inicio")
    private LocalDate dataInicio;

    @Column(length = 10, name = "Data_Fim")
    private LocalDate dataFim;



}
