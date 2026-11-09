package org.pires.springstudy.infrastructure.repository;

import org.jspecify.annotations.NonNull;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    List<Aluno> findAllByNome(String nome);

    Optional<Aluno> findByCPF(String cpf);


    boolean existsByCPF(String cpf);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);

    boolean existsById(@NonNull UUID id);


}
