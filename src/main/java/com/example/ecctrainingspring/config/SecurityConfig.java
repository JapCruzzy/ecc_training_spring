package com.example.ecctrainingspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    //default user and password for each role
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
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf().disable()
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/**").permitAll()
//                request.requestMatchers("/api/employees/list",
//                                "/api/employees/view/**",
//                                "/api/tickets/list",
//                                "/api/tickets/view/**")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/employees/create",
//                                "/api/tickets/create")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/employees/update/**",
//                                "/api/tickets/update/**",
//                                "/api/employees/assign-ticket/**",
//                                "/api/employees/add-watchers/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/employees/delete/**",
//                                "/api/tickets/delete/**")
//                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .httpBasic();
        return http.build();

    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource()
//    {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
