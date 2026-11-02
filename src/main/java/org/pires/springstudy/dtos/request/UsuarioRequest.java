package org.pires.springstudy.dtos.request;

public record UsuarioRequest(String nome, String sobrenome, String identificadorLogin, String senha) {
}
