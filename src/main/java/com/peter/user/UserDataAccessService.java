package com.peter.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDataAccessService implements UserDao{

    private final UserRepository userRepository;

    public UserDataAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> selectAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> selectUserByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsUserWithUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
