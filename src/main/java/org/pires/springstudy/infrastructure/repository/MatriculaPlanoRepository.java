package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatriculaPlanoRepository extends JpaRepository<MatriculaPlano, UUID> {
    @Query("""
    SELECT DISTINCT a FROM MatriculaPlano mp JOIN mp.matricula m JOIN m.aluno a WHERE mp.plano.id = :planoId AND mp.dataFim IS NULL
    """)
    List<Aluno> findAlunosByPlanoId(@Param("planoId") Long planoId);


    // DataFim nula identifica o vínculo de plano que ainda está ATUAL .
    List<MatriculaPlano> findAllByMatriculaAndDataFimIsNull(Matricula matricula);


}
