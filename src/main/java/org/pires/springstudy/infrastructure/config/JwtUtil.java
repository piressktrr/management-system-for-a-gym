package org.pires.springstudy.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    // interface que gera uma secretkey para a nossa aplicação no servidor, comigo passando um algoritmo de hash
    private final SecretKey secretKey;


    public JwtUtil() {
        // só entendi que o Jwts é uma classe factory que implementa os algoritmos de hash, criptaçao e keys
        // mas ele vai gerar uma chave secreta para o algoritmo de assinatura HMAC usando sha-256
        this.secretKey = Jwts.SIG.HS256.key().build();

        // chave só é gerada em memoria
    }



    // token JWT com validade de 1 hora
    public String generateToken(String payload) {

        // gera um token JWT com as claims, tempo de expiração de uma hora e assinatura da chave secreta do construtor usando a interface q eu injetei as depedencias
        return Jwts.builder()
                .subject(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // token valido por 1 hora
                .signWith(secretKey)
                .compact();
    }

    // Extrai as claims de um token JWT (infos adicionais)
    public Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey) // define a chave secreta para validar a assinatura do Token
                .build()
                .parseSignedClaims(token) // analisa o JWT e obtem as claims
                .getPayload(); // retorna o payload das claims

    }

    public String extractUsername(String token) {
        //  obtém o nome do usuario das claims (o subject)
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {

        return extractClaims(token).getExpiration().before(new Date()); // vai retornar se o token ta expirado, sim ou nao
    }

    // valida se o token não está expirado e se o nome de usuario esta correto
    public boolean validateToken(String token, String payload) {

        // pega o nome extraido do subjewct
        final String username = extractUsername(token);

        // returna sim ou nao se o nome é igual ao payload fornecedio e se o token não está expirado
        return (username.equals(payload) && !isTokenExpired(token));
    }

}
