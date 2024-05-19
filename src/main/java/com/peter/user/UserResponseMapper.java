package com.peter.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserResponseMapper implements Function<User, UserResponse> {

    @Override
    public UserResponse apply(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getBiography()
        );
    }
}
