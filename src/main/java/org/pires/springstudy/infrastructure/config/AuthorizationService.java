package org.pires.springstudy.infrastructure.config;

import org.jspecify.annotations.NullMarked;
import org.pires.springstudy.dtos.UsuarioRequest;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public AuthorizationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // implementaçao para carregar detalhes do usuario pelo identificador
    @Override
    public @NullMarked UserDetails  loadUserByUsername(String identificadorLogin) throws UsernameNotFoundException {

        // busca o usuario pelo identificador no banco, se não houver e retornar null, retorna exceção
        UsuarioRequest usuarioRequest = usuarioRepository.findByIdentificadorLogin(identificadorLogin)
                .orElseThrow(() -> new UsernameNotFoundException("Identificador not found: " + identificadorLogin));

        // cria e retorna um novo objeto UserDetails com base no usuario encontrado
        return User
                .withUsername(usuarioRequest.identificadorLogin())
                .password(usuarioRequest.senha())
                .build();
    }
}
