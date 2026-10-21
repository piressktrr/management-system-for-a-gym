package org.pires.springstudy.controller;

import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.saveUser(usuario)); //  retorna um ok (status code 200) desse post que eu estou fazendo
    }

}
