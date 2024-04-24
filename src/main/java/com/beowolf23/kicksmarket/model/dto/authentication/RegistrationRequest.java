package com.beowolf23.kicksmarket.model.dto.authentication;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @Email(message = "Email should be valid", regexp = ".+@.+\\..+")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Username is mandatory")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotNull(message = "Password should not be empty")
    @Size(min = 4, max = 16, message = "The password must have a length between 4 and 16 characters")
    private String password;
}
