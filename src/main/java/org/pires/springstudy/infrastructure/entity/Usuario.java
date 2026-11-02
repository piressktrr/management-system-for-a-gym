package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    public Usuario(String nome, String sobrenome, String identificadorLogin, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.identificadorLogin = identificadorLogin;
        this.senha = senha;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,  length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @Column(nullable = false, name = "login_email", length = 50, unique = true) // mesmo checando no service, o unique == true mantem a unicidade no banco
    private String identificadorLogin; // email que vai ser usado como login do usuario

    @Column(nullable = false)
    private String senha; // senha guardada como hash


    //    @Column(nullable = false)
//    private UserRole role;

//    @Override
//    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
//        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
//        return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public  @NullMarked String getPassword() {
        return senha;
    } // ver porque a resposta ta retornando o password (senha) e o username (identificador) junto com a senha e o identificador, deixar so um dos dois

    @Override
    public  @NullMarked String getUsername() {
        return identificadorLogin;
    }
}
