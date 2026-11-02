package org.pires.springstudy.dtos;

public record UsuarioRequest(String nome, String sobrenome, String identificadorLogin, String senha) {
}
