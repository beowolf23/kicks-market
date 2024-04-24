package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.model.dao.Role;
import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.model.dto.authentication.AuthenticationRequest;
import com.beowolf23.kicksmarket.model.dto.authentication.AuthenticationResponse;
import com.beowolf23.kicksmarket.model.dto.authentication.RegistrationRequest;
import com.beowolf23.kicksmarket.repository.UserRepository;
import com.beowolf23.kicksmarket.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
