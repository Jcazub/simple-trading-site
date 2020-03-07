package com.trading.service;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user) throws MalformedObjectException;
    User editUser(User user) throws MalformedObjectException, UserNotFoundException;
    User getUser(int userId) throws UserNotFoundException;
    User getUserByEmail(String email) throws UserNotFoundException;
    void deleteUser(int userId) throws UserNotFoundException;

    List<User> getAllUsers();
}
