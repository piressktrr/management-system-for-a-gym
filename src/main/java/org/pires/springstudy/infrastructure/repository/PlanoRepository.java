package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long>
{


}
