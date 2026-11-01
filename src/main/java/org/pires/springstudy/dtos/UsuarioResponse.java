package org.pires.springstudy.dtos;


public record UsuarioResponse(String nome, String sobrenome, String identificadorLogin, String senha) {

    public UsuarioResponse(String nome, String sobrenome, String identificadorLogin, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.identificadorLogin = identificadorLogin;
        this.senha = senha;
    }

    public UsuarioResponse(String nome, String sobrenome, String identificadorLogin) {
        this(nome, sobrenome, identificadorLogin, "");
    }

    public UsuarioResponse(String identificadorLogin, String senha) {
        this("", "", identificadorLogin, senha);
    }
}
