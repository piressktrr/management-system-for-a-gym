package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class UsuarioAuthController {

    private final AuthenticationManager authenticationManager;

    public UsuarioAuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO usuario) {
        var loginPassword = new UsernamePasswordAuthenticationToken(usuario.identificadorLogin(), usuario.senha());
        var authentication = authenticationManager.authenticate(loginPassword);
        // encriptografa a senha passada por parametro e compara com a que ta no banco de dados

        return ResponseEntity.ok().build();
    }
}
