package com.example.demo.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Configuration // Marca a classe como uma configuração a ser utilizada no Spring
@EnableWebSecurity // Ativa o SpringSecurity
public class SecurityConfiguration {

    @Bean // Carrega previamente, preparando para uso
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        return httpSecurity.csrf(csrf -> csrf.disable()).
                sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // permitAll faz com que qualquer um possa acessar
                        .requestMatchers(HttpMethod.POST, "/pessoas").hasRole("ADMIN") //hasRole faz com que somente o cargo designado acesse
                        .anyRequest().authenticated()
                )
                .build();

    };

    @Bean // Carrega previamente, preparando para uso
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
        }

    @Bean // Carrega previamente, preparando para uso
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
