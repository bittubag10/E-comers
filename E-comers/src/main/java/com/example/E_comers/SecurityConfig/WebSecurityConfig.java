package com.example.E_comers.SecurityConfig;

import com.example.E_comers.ENUM.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.E_comers.ENUM.RoleType.*;



@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig ->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                        .requestMatchers("customer/**").hasAnyRole(CUSTOMER.name(),ADMIN.name())
                        .requestMatchers("/product/**").hasAnyRole(CUSTOMER.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oAuth2 ->oAuth2
                        .failureHandler((AuthenticationFailureHandler) (request, response, exception) -> {
                            log.error("OAuth2 error: {}",exception.getMessage());
                        })
                        .successHandler(oAuth2SuccessHandler)

                );
        return httpSecurity.build();
    }


}
