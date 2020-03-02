package service;

import model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User editUser(User user);
    User getUser(int userId);
    void deleteUser(int userId);

    List<User> getAllUsers();
}
