package org.pires.springstudy.dtos.usuarioDtos;

public record UsuarioRequest(String nome, String sobrenome, String identificadorLogin, String senha) {
}
