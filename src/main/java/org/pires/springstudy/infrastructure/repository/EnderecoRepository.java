package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {


    Optional<Endereco> findByNumeroAndComplementoAndCep(String numero, String complemento, String cep);
}
