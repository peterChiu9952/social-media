package com.peter.user;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> selectAllUsers();
    Optional<User> selectUserByUserId(Long userId);
    void insertUser(User user);
    boolean existsUserWithUsername(String username);
    Optional<User> selectUserByUsername(String username);
}
