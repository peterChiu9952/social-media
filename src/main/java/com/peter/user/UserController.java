package com.peter.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{userid}")
    public ResponseEntity<UserResponse> getUserByUserId(@PathVariable long userid) {
        UserResponse userResponse = userService.getUserByUserId(userid);
        return ResponseEntity.ok()
                .body(userResponse);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok()
                .build();
    }
}
