package com.beowolf23.kicksmarket.security.configs;

import com.beowolf23.kicksmarket.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsServiceConfiguration {

    private final UserRepository userRepository;

    public UserDetailsServiceConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
