package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
