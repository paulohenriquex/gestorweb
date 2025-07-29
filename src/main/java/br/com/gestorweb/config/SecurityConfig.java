package br.com.gestorweb.config;

import br.com.gestorweb.service.CustomUserDetailsService; // Importe sua classe CustomUserDetailsService
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Injete o CustomUserDetailsService
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
                .authorizeHttpRequests(authz -> authz
                        // Permite POST para criação de usuário e login sem autenticação.
                        .requestMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/login").permitAll()
                        // Todas as outras requisições (incluindo POST /api/marcas) exigirão
                        // autenticação.
                        .anyRequest().authenticated())
                // Habilita autenticação HTTP Basic para facilitar testes com Postman
                .httpBasic(org.springframework.security.config.Customizer.withDefaults());
        return http.build();
    }

    // Sobrescreve o bean UserDetailsService para usar sua implementação customizada
    @Bean
    // REMOVIDO A ANOTAÇÃO @Override aqui
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }
}
