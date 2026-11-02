package org.pires.springstudy.infrastructure.repository;

import org.pires.springstudy.infrastructure.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    boolean existsByCPF(String cpf);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
}
