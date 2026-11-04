package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
}
