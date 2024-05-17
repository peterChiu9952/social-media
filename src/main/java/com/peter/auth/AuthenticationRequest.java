package com.peter.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
