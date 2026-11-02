package org.pires.springstudy.infrastructure.config;

import org.jspecify.annotations.NullMarked;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationConfig implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public AuthorizationConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // implementaçao para carregar detalhes do usuario pelo identificador
    @Override
    public @NullMarked UserDetails  loadUserByUsername(String identificadorLogin) throws UsernameNotFoundException {

        // busca o usuario pelo identificador no banco, se não houver e retornar null, retorna exceção
        Usuario usuario = usuarioRepository.findByIdentificadorLogin(identificadorLogin)
                .orElseThrow(() -> new UsernameNotFoundException("Identificador not found: " + identificadorLogin));



        // cria e retorna um novo objeto UserDetails com base no usuario encontrado
        return User
                .withUsername(usuario.getIdentificadorLogin())
                .password(usuario.getSenha())
                .build();
    }
}
