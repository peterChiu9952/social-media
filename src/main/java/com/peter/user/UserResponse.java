package com.peter.user;

public record UserResponse(
        Long userId,
        String username,
        String email,
        String biography
) {
}
