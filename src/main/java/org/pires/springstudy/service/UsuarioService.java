package org.pires.springstudy.service;

import org.pires.springstudy.infrastructure.entity.Usuario;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateUserLogin;
import org.pires.springstudy.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService { // regras de negocio + lógica
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUser(Usuario usuario) {
        try {
            checkLoginSaveUser(usuario);
            return usuarioRepository.save(usuario);
        } catch (ConflictByCreateUserLogin e) {
            throw new ConflictByCreateUserLogin(e.getCause().getMessage());
        }
    }

    public void checkLoginSaveUser(Usuario usuario) { // regra de negocio para verificar se o email já existe na hora de cadastrar usuário
        try {
            if (checkLoginDataBase(usuario.getIdentificadorLogin())) {
                throw new ConflictByCreateUserLogin("Email já cadastrado para usuário");
            }
        }catch (ConflictByCreateUserLogin e){
            System.out.println(e.getCause().getMessage());
        }
    }

    public boolean checkLoginDataBase(String identificadorLogin) {
        return usuarioRepository.existsByLogin(identificadorLogin); // checa para ver se o email ta cadastrado no banco de dados, pode ser usado para outras funções
    }
}
