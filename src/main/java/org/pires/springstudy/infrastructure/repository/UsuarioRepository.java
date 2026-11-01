package org.pires.springstudy.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.pires.springstudy.dtos.LoginRequest;
import org.pires.springstudy.dtos.UsuarioRequest;
import org.pires.springstudy.dtos.UsuarioResponse;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByidentificadorLogin(String identificadorLogin);

    Optional<UsuarioResponse> findByIdentificadorLogin(String identificadorLogin);


    @Transactional // n sei oq faz
    void deleteUserByIdentificadorLogin(String identificadorLogin);
}