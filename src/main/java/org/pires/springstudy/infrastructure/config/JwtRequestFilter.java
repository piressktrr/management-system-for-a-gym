package org.pires.springstudy.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// estende a classe que é responsavel por gerenciar o servlet de request e response
public class JwtRequestFilter extends OncePerRequestFilter {

    // propriedades para armazenar o jwt e o user details
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    // método que é chamado uma vez por requisição para fazer a validação usando esse filtro aqui
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // pega o valor do Authorization header da requisiçao
        final String authorizationHeader = request.getHeader("Authorization");

        // se nao for nulo e começar com Bearer (padrao q o JWT tem que seguir)
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // pega o token JWT que vem depois do bearer (6 letras + espaço)
            final String token = authorizationHeader.substring("Bearer ".length());

            // pega o payload do token JWT (que ta codificado em Base64 depois do primeiro ponto -> header.payload.signature)
            final String payload = jwtUtil.extractUsername(token);

            // se o payload não for nulo e o usuário nao estiver autenticado ainda
            if (payload != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // extrai os detalhes de usuario dele pelo nome (payload)
                UserDetails userDetails = userDetailsService.loadUserByUsername(payload);

                // se o método que ta utilizando o token e o payload for verdadeiro (validando o token jwt)
                if (jwtUtil.validateToken(token, payload)) {

                    // cria um token JWT (objeto) de autenticaçao para o usuario, passando seu userdetails, sem credenciais e suas autorizaçoes
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }

            }

        }

        // continua a cadeia dos proximos filtros, permitindo que a requisição prossiga
        filterChain.doFilter(request, response);
    }

}
