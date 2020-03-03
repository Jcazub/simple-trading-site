package service;

import exceptions.MalformedObjectException;
import exceptions.UserNotFoundException;
import model.User;

import java.util.List;

public interface UserService {
    User addUser(User user) throws MalformedObjectException;
    User editUser(User user) throws MalformedObjectException, UserNotFoundException;
    User getUser(int userId) throws UserNotFoundException;
    void deleteUser(int userId) throws UserNotFoundException;

    List<User> getAllUsers();
}
