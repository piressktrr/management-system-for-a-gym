package org.pires.springstudy.service;

import org.pires.springstudy.dtos.UsuarioRequest;
import org.pires.springstudy.dtos.UsuarioResponse;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateUserLogin;
import org.pires.springstudy.infrastructure.exceptions.ResourceNotFoundException;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


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

        // ele ta retornando a senha aqui e no find user by email, eu tenho que dar um jeito de só buscar o nome, sobrenome e login
        var response = usuarioRepository.save(new Usuario(usuarioRequest.nome(), usuarioRequest.sobrenome(), usuarioRequest.identificadorLogin(), pwdEncrypted));

        return new UsuarioResponse(response.getNome(), response.getSobrenome(), response.getIdentificadorLogin()); // colocar pra retornar so as credenciais importantes
    }

    public UsuarioResponse findUserByEmail(@RequestParam String identificadorLogin){

        Usuario usuario = usuarioRepository.findByIdentificadorLogin(identificadorLogin)
                .orElseThrow(() -> new ResourceNotFoundException("Identificador not found: " + identificadorLogin));

        return new UsuarioResponse(usuario.getNome(), usuario.getSobrenome(), usuario.getIdentificadorLogin());
    }

    public void deleteUserByEmail(String identificadorLogin){
        usuarioRepository.deleteUserByIdentificadorLogin(identificadorLogin);
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
