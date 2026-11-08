package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
}
