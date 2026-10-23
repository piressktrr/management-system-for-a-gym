package org.pires.springstudy.service;

import org.jspecify.annotations.NullMarked;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
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

    @Override
    public @NullMarked UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByIdentificadorLogin(username);
    }
}
