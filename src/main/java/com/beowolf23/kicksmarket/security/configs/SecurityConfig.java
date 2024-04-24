package com.beowolf23.kicksmarket.security.configs;

import com.beowolf23.kicksmarket.model.dao.Role;
import com.beowolf23.kicksmarket.security.providers.MihaiAuthenticationProvider;
import com.beowolf23.kicksmarket.security.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.SessionManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          AuthenticationProvider authProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> {

                    // SWAGGER
                    requests.requestMatchers("/login",
                            "/register",
                            "/swagger-ui/**",
                            "/v2/api-docs",
                            "/swagger-resources",
                            "/swagger-resources/**",
                            "/configuration/ui",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/v3/api-docs/**",
                            "/swagger-ui/**").permitAll();
                    // AUTHENTICATION
                    requests.requestMatchers("/auth/**").permitAll();

                    // PRODUCTS
                    requests.requestMatchers(HttpMethod.GET, "/products/**").permitAll();
                    requests.requestMatchers(HttpMethod.POST, "/products/**").hasAnyAuthority("ADMIN");

                    // USERS
                    requests.requestMatchers("/users/**").authenticated();

                    // ADDRESSES
                    requests.requestMatchers("/addresses").authenticated();

                    // REVIEWS
                    requests.requestMatchers(HttpMethod.DELETE, "/reviews").hasAnyAuthority("ADMIN");
                    requests.requestMatchers("/reviews/**").authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

}
