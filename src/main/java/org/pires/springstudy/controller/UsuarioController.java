package org.pires.springstudy.controller;

import org.pires.springstudy.service.UsuarioService;
import org.springframework.web.bind.annotation.RestController;

@RestController("usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


}
