package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatriculaPlanoRepository extends JpaRepository<MatriculaPlano, UUID> {
}
