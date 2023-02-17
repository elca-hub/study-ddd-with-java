package com.studydddwithjava.school.infrastructure.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/auth/**").authenticated()
                        .requestMatchers("/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .passwordParameter("pw")
                        .usernameParameter("username")
                        .defaultSuccessUrl("/auth/")
                )
                .logout((logout) -> logout
                        .permitAll()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                );

        return http.build();
    }
}
