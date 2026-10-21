package org.pires.springstudy.service;

import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario criarNovoUsuario(UsuarioLoginDTO usuarioLoginDTO) {
        return new Usuario();
    }
}
