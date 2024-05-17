package com.peter.auth;

import com.peter.user.UserDTO;

public record AuthenticationResponse(
        String token,
        Long userId
) {
}
