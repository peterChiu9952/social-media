package com.peter.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotBlank
        @Pattern(regexp = "09\\d{8}", message = "Invalid Taiwan phone number")
        String username,
        @Email
        String email,
        @NotBlank
        String password,
        String biography) {
}
