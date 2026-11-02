package org.pires.springstudy.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByidentificadorLogin(String identificadorLogin); // repositorio sempre deve retornar a entidade

    Optional<Usuario> findByIdentificadorLogin(String identificadorLogin);


    @Transactional // n sei oq faz
    void deleteUserByIdentificadorLogin(String identificadorLogin);
}