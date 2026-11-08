package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.request.user.UsuarioRequest;
import org.pires.springstudy.dtos.response.user.UsuarioResponse;
import org.pires.springstudy.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController { // logica de crud

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> saveUser(@RequestBody UsuarioRequest usuarioRequest) {

        return ResponseEntity.ok(usuarioService.saveUser(usuarioRequest)); //  retorna um ok (status code 200) desse post p/ salvar usuario que eu estou fazendo
    }

    @GetMapping("/search")
    public ResponseEntity<UsuarioResponse> searchUserByEmail(@RequestParam("email") String identificadorLogin){ /// parametro

        return ResponseEntity.ok(usuarioService.findUserByEmail(identificadorLogin));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable("email") String identificadorLogin){ /// variavel
        usuarioService.deleteUserByEmail(identificadorLogin);
        return ResponseEntity.ok().build();
    }



//    @GetMapping
//    public ResponseEntity<List<Usuario>> getAllUsers() {
//        List<Usuario> users = this.usuarioService.getAllUsers();
//
//        return ResponseEntity.ok(users);
//    }

}
