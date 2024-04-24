package com.beowolf23.kicksmarket.beans;

import com.beowolf23.kicksmarket.model.dto.authentication.RegistrationRequest;
import com.beowolf23.kicksmarket.service.AuthenticationService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private AuthenticationService authenticationService;

    public DataLoader(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        authenticationService.register(
//                RegistrationRequest.builder()
//                        .email("mihaiciocan44@gmail.com")
//                        .username("mihaiciocan44")
//                        .password("password")
//                        .build()
//        );
//
//        authenticationService.register(
//                RegistrationRequest.builder()
//                        .email("john.doe@gmail.com")
//                        .username("john.doe")
//                        .password("password")
//                        .build()
//        );
    }
}
