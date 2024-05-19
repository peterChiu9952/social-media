package com.peter.auth;

import com.peter.jwt.JWTUtil;
import com.peter.user.User;
import com.peter.user.UserResponse;
import com.peter.user.UserResponseMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserResponseMapper userResponseMapper;
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, UserResponseMapper userResponseMapper, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userResponseMapper = userResponseMapper;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        User principal = (User) authenticate.getPrincipal();
        UserResponse userResponse = userResponseMapper.apply(principal);
        String token = jwtUtil.issueToken(userResponse.username());

        return new AuthenticationResponse(token, userResponse.userId());
    }
}
