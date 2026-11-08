package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Contrato;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    // Recupera o contrato que será encerrado junto com a matrícula.
    Optional<Contrato> findByMatricula(Matricula matricula);
}
