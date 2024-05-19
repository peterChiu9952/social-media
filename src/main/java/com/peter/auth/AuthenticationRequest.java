package com.peter.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequest(
        @NotBlank
        @Pattern(regexp = "09\\d{8}", message = "Invalid Taiwan phone number")
        String username,
        @NotBlank
        String password
) {
}
