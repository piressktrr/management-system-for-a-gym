package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
}
