package com.trading.dao;

import com.trading.model.User;

import java.util.List;

public interface UserDao {
    User addUser(User user);
    User editUser(User user);
    User getUser(int userId);
    void deleteUser(int userId);

    List<User> getAllUsers();
}
