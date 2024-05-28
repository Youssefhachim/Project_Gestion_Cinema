package com.example.gestion_des_cinema.config;



import com.example.gestion_des_cinema.dao.entities.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/static/**", "/css/**", "/javascript/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {

            String redirectUrl = "/";
            if (authentication.getPrincipal() instanceof MyUserPrincipal myUserPrincipal) {
                Client creator = myUserPrincipal.getCreatorDTO();
                if (creator.getRole() == null) {
                    redirectUrl = "/error";
                } else if (creator.getRole().equalsIgnoreCase("admin")) {
                    redirectUrl = "/admin";
                }
            }
            response.sendRedirect(redirectUrl);
        };
    }

}
