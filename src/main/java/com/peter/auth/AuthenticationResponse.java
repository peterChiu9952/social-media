package com.peter.auth;

public record AuthenticationResponse(
        String token,
        Long userId
) {
}
