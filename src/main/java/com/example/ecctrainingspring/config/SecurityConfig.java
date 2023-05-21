package com.example.ecctrainingspring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

    //default user and password for each roles
    @Bean
    public InMemoryUserDetailsManager configure() throws Exception {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public SecurityFilterChain employeeAndTicketsFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(request ->
                request.requestMatchers("/api/employees/list",
                                "/api/employees/view/**",
                                "/api/tickets/list",
                                "/api/tickets/view/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/employees/create",
                                "/api/tickets/create")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/employees/update/**",
                                "/api/tickets/update/**",
                                "/api/employees/assign-ticket/**",
                                "/api/employees/add-watchers/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/delete/**",
                                "/api/tickets/delete/**")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .httpBasic();
        return http.build();


    }

}
