package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.LoginRequest;
import org.pires.springstudy.dtos.LoginResponse;
import org.pires.springstudy.dtos.UsuarioRequest;
import org.pires.springstudy.infrastructure.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

// Sessao, segurança, identidade -> autenticaçao de credenciais, jwt, esqueceu a senha etc.
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Esse post ta seguindo o principio de responsabilidade única
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest usuario) {

        // fluxo -> pegar o usuario autenticado, gerar o JWT para ele, devolver esse token no corpo da resposta (LoginResponse)


        // cria uma junção de login + senha + autenticaçao
        var identificadorLoginPassword = new UsernamePasswordAuthenticationToken(usuario.identificadorLogin(), usuario.senha());

        // se for valido ->
        // Cria um authentication que ta validando o UsernamePasswordAuthenticationToken, retornando true or false para a validação
        var authentication = authenticationManager.authenticate(identificadorLoginPassword); // se for valido ->

        // Aqui eu pego e gero um token com o name (identificadorLogin) desse authentication (autenticaçao criada e validada do login + senha)
        String  JWT = this.jwtUtil.generateToken(authentication.getName());


        return ResponseEntity.ok(new LoginResponse(JWT));

    }
}
