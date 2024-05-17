package com.peter.user;

import com.peter.exception.DuplicateResourceException;
import com.peter.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return userDao.selectAllUsers()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDTO getUserByUserId(long userId) {
        return userDao.selectUserByUserId(userId)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    public void createUser(UserRequest request) {
        String username = request.username();
        if (userDao.existsUserWithUsername(username)) {
            throw new DuplicateResourceException("username already taken.");
        }
        userDao.insertUser(new User(
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.biography()
        ));
    }
}
