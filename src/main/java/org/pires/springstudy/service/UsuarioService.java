package org.pires.springstudy.service;

import lombok.extern.log4j.Log4j2;
import org.pires.springstudy.dtos.UsuarioRequest;
import org.pires.springstudy.dtos.UsuarioResponse;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateUserLogin;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService { // regras de negocio + lógica
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // ta injetando a dependencia que ja ta configurada no security config, no caso o bcrypt


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse saveUser(UsuarioRequest usuarioRequest) {
        checkLoginSaveUser(usuarioRequest.identificadorLogin());

        String pwdEncrypted = passwordEncoder.encode(usuarioRequest.senha());


        usuarioRepository.save(new Usuario(usuarioRequest.nome(), usuarioRequest.sobrenome(), usuarioRequest.identificadorLogin(), pwdEncrypted));

        return new UsuarioResponse(usuarioRequest.nome(), usuarioRequest.sobrenome(), usuarioRequest.identificadorLogin()); // colocar pra retornar so as credenciais importantes
    }

    public void checkLoginSaveUser(String identificadorLogin) { // regra de negocio para verificar se o email já existe na hora de cadastrar usuário
        if (checkLoginDataBase(identificadorLogin)) {
            throw new ConflictByCreateUserLogin(HttpStatus.CONFLICT, ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Email ja cadastrado para usuario"), new Throwable());
        }

    }

    public boolean checkLoginDataBase(String identificadorLogin) {
        return usuarioRepository.existsByidentificadorLogin(identificadorLogin); // checa para ver se o email ta cadastrado no banco de dados, pode ser usado para outras funções
    }

//    public List<Usuario> getAllUsers() {
//        return usuarioRepository.findAll();
//    }
}
