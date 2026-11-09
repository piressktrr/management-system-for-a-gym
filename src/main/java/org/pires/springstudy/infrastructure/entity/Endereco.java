package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pires.springstudy.infrastructure.entity.enums.Estado;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco", uniqueConstraints = @UniqueConstraint(name = "uk_endereco_cep_numero_complemento", columnNames = {"numero", "complemento", "cep"}))
// a constraint aqui em cima restringe que duas requisições possam tentar inserir na mesma tupla
// mas ela permite com que um endereço seja reutilizado
public class Endereco {

    public Endereco(String rua, String numero, String complemento, String bairro, String municipio, Estado estado, String pais, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(length = 50, nullable = false)
    private String complemento;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String municipio;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false, length = 60)
    private String pais;

    @Column(nullable = false, length = 8)
    private String cep;



}
