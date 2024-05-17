package com.peter.user;

import com.peter.jwt.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public UserController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{userid}")
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable long userid) {
        UserDTO userDTO = userService.getUserByUserId(userid);
        return ResponseEntity.ok()
                .body(userDTO);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        userService.createUser(request);
//        String jwtToken = jwtUtil.issueToken(request.username(), "ROLE_USER");
        return ResponseEntity.ok()
//                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }
}
