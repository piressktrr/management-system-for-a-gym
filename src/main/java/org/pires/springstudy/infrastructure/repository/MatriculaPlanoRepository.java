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
    SELECT DISTINCT alunos FROM MatriculaPlano mp JOIN Matricula m JOIN Aluno alunos WHERE mp.plano = :planoId AND mp.dataFim = null
    """)
    List<Aluno> findAlunosByPlanoId(@Param("planoId") Long planoId);


    // DataFim nula identifica o vínculo de plano que ainda está ATUAL .
    List<MatriculaPlano> findAllByMatriculaAndDataFimIsNull(Matricula matricula);


}
