package org.pires.springstudy.infrastructure.repository;


import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, UUID> {
}
