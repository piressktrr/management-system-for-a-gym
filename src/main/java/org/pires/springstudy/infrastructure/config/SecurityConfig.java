package org.pires.springstudy.infrastructure.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }



    // Configuração do filtro de segurança (que sempre é ativado antes de chegar no Controller)
    // Só usuarios com role de admin podem criar usuarios novos
    // Senha salva com hash no banco de dados
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // cria um novo filtro de requisiçao passando o token JWT e os detalhes do User (ja validados pelo service)
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(jwtUtil, userDetailsService);

        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configure(http)) // configura a validação de CORS
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD,  DispatcherType.ERROR).permitAll() // permito que o ERROR e FORWARD não precisam estar autenticados pra fazer tratamento de exceçao
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permite que todos possam fazer login, o de register por enquanto é so pra teste, mas o certo é deixar so usuarios acessarem p cadastrarem outros usuarios
                        .requestMatchers(HttpMethod.GET, "/usuario/**").permitAll()// Permite que qualquer requisiçao get no usuario nao precisa estar autenticada
                        .requestMatchers(HttpMethod.POST, "/usuario/register").permitAll()// Permite que todos possam se cadastrar
                        .requestMatchers(HttpMethod.POST, "/alunos/**").authenticated()
                        .anyRequest().authenticated() // Qualquer outra requisiçao na api tera que estar com um usuario autenticado
                )

                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // diz que a API é stateless (padrao rest)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Configura o filtro que vai ser passado antes,
                // No caso o JwtRequestFilter junto com um UserNamePassWordFilter

                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager(); //  Obtem o AuthenticationManager, que nesse caso ta vindo do UserAuthentication
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Retorna uma nova instancia do BCrypt, algoritmo utilizado para hashear senhas

    }
}
