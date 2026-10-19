package org.pires.springstudy.controller;

import org.pires.springstudy.DTOs.UsuarioLoginDTO;
import org.pires.springstudy.domain.Usuario;
import org.pires.springstudy.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("login")
public class Login {
    private final UsuarioService usuarioService;

    public Login(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public void login(String login, String senha) {
        usuarioService.criarNovoUsuario(new UsuarioLoginDTO(login, senha));
    }
}
