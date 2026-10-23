package org.pires.springstudy.service.users;

import lombok.extern.log4j.Log4j2;
import org.pires.springstudy.dtos.UsuarioDTO;
import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateUserLogin;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UsuarioService { // regras de negocio + lógica
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUser(UsuarioDTO usuarioDTO) {
        String pwdEncrypted = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.sobrenome(), usuarioDTO.identificadorLogin(), pwdEncrypted);

        checkLoginSaveUser(usuario);
        return usuarioRepository.save(usuario);
    }


    public void checkLoginSaveUser(Usuario usuario) { // regra de negocio para verificar se o email já existe na hora de cadastrar usuário
        try {
            if (checkLoginDataBase(usuario.getIdentificadorLogin())) {
                throw new ConflictByCreateUserLogin(HttpStatus.CONFLICT, ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Email ja cadastrado para usuario"), new Throwable());
            }
        }catch (ConflictByCreateUserLogin e){
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
