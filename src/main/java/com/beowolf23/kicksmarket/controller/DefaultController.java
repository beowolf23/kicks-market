package com.beowolf23.kicksmarket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String homepage(Authentication authentication) {
//        return "Welcome to the VIP Room ~[ " +
//                authentication.getCredentials()
//                + " ]~ 😆";
        return "Hello";
    }
}
