package org.pires.springstudy.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,  length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @Column(nullable = false, name = "login_email", length = 50)
    private String identificadorLogin; // email que vai ser usado como login do usuario

    @Column(nullable = false)
    private String senha_hash; // senha guardada como hash


    @Override
    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public  @NullMarked String getPassword() {
        return senha_hash;
    }

    @Override
    public  @NullMarked String getUsername() {
        return identificadorLogin;
    }
}
