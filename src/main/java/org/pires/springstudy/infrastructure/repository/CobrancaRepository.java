package org.pires.springstudy.infrastructure.repository;


import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, UUID> {

    @Query("""
        SELECT DISTINCT a
        FROM Cobranca c
        JOIN c.idMatriculaPlano mp
        JOIN mp.matricula m
        JOIN m.aluno a
        WHERE c.statusCobranca = :status
        """) // faz um query passando parametro de status para retornar uma lista de alunos com o status da cobrança
    // fazendo varios joins e usando distinct para nao retornar alunos repetidos caso ele tenha mais de uma cobrança
    // com aql status
    List<Aluno> findAlunosByStatusCobranca(@Param("status") StatusCobranca statusCobranca);

    @Query("""
        SELECT DISTINCT a
        FROM Cobranca c
        JOIN c.idMatriculaPlano mp
        JOIN mp.matricula m
        JOIN m.aluno a
        WHERE c.statusCobranca = StatusCobranca.PENDENTE AND c.dataDeVencimento < :dataAtual
        """)
        // faz um query passando parametro de status para retornar uma lista de alunos com o status da cobrança pendente e data anterior a atual
        // fazendo varios joins e usando distinct para nao retornar alunos repetidos caso ele tenha mais de uma cobrança
        // com aquele status
    List<Aluno> findAlunosByStatusCobrancaPendenteAndDataVencimentoBeforeActualDate(
            @Param("dataAtual") LocalDateTime localDateTime);
}
