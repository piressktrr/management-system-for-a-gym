package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
    Optional<Matricula> findByAluno_Id(UUID alunoId);

    @Query("""
    SELECT matriculas FROM Matricula matriculas WHERE matriculas.aluno.id = :alunoId
""")
    List<Matricula> findAllMatriculasByAluno(@Param("alunoId")  UUID alunoId);

}
