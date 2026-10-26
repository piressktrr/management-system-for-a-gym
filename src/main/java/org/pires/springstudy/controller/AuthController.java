package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.LoginRequest;
import org.pires.springstudy.dtos.UsuarioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController { // sessao, segurança, identidade -> autenticaçao de credenciais, jwt, esqueceu a senha etc.

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login") // ta seguindo o principio de responsabilidade unica
    public ResponseEntity<UsuarioRequest> login(@RequestBody LoginRequest usuario) {
        var loginPassword = new UsernamePasswordAuthenticationToken(usuario.identificadorLogin(), usuario.senha());
        var authentication = authenticationManager.authenticate(loginPassword);

        // encriptografa a senha passada por parametro e compara com a que ta no banco de dados

        return ResponseEntity.ok().build(); // aqui que eu tenho que retornar o token jwt para manter o usuario logado


        // fluxo -> pegar o usuario autenticado, gerar o JWT para ele, devolver esse token no corpo da resposta (LoginResponse)
    }
}
