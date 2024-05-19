package com.peter.user;

public record UserRequest(
        String username,
        String email,
        String password,
        String biography) {
}
