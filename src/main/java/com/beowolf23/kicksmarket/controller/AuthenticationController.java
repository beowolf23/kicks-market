package com.beowolf23.kicksmarket.controller;

import com.beowolf23.kicksmarket.model.dto.AuthenticationRequest;
import com.beowolf23.kicksmarket.model.dto.AuthenticationResponse;
import com.beowolf23.kicksmarket.model.dto.RegistrationRequest;
import com.beowolf23.kicksmarket.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegistrationRequest request
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }
}
