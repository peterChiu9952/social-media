package com.peter.user;

public record UserDTO(
        Long userId,
        String username,
        String email,
        String biography
) {
}
