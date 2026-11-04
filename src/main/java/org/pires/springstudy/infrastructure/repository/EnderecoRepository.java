package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {


    Optional<Endereco> findByNumeroAndComplementoAndCep(Integer numero, String complemento, String cep);
}
