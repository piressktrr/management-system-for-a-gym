package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.UsuarioDTO;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.service.users.UsuarioService;
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
    @PostMapping("/register")
    public ResponseEntity<Usuario> saveUser(@RequestBody UsuarioDTO usuarioDTO) {

        return ResponseEntity.ok(usuarioService.saveUser(usuarioDTO)); //  retorna um ok (status code 200) desse post p/ salvar usuario que eu estou fazendo
    }


//    @GetMapping
//    public ResponseEntity<List<Usuario>> getAllUsers() {
//        List<Usuario> users = this.usuarioService.getAllUsers();
//
//        return ResponseEntity.ok(users);
//    }

}
