package com.shashank.taskflow.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.
                    authorizeHttpRequests(auth -> auth
                            .requestMatchers("/auth/**").permitAll()
                            .anyRequest().authenticated())
                    .csrf(csrfConfig -> csrfConfig
                            .disable())
                    .sessionManagement(sessionConfig -> sessionConfig
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .logout(Customizer.withDefaults());
            return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
