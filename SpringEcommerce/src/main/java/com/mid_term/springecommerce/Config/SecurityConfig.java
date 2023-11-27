package com.mid_term.springecommerce.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceConfig();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        AuthenticationManager manager = builder.build();
        http
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/", "/auth/**", "/api/users/**","/api/products/**", "/product/**", "/home", "/contact","/shop", "/api/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedPage("/auth/error")
                )
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/do-login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/auth/login?error")
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/auth/login")
                )
                .csrf(
                        csrf -> csrf
                                .disable()
                                .authenticationManager(manager));
        return http.build();
    }
}
