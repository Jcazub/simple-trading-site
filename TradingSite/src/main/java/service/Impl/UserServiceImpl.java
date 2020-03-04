package service.Impl;

import dao.UserDao;
import exceptions.MalformedObjectException;
import exceptions.UserNotFoundException;
import model.User;
import service.UserService;
import utilites.VerificationHelper;

import java.util.List;

import static utilites.VerificationHelper.isStringInvalid;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public User addUser(User user) throws MalformedObjectException {
        if (verifyUserNotNull(user)) {
            return userDao.addUser(user);
        } else {
            throw new MalformedObjectException();
        }
    }

    @Override
    public User editUser(User user) throws MalformedObjectException, UserNotFoundException {
        if (!verifyUserNotNull(user)) {
            throw new MalformedObjectException();
        } else if (user.getUserId() < 1) {
            throw new UserNotFoundException();
        }
        return userDao.editUser(user);
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException {
        if (userId < 1) throw new UserNotFoundException();

        return userDao.getUser(userId);
    }

    @Override
    public void deleteUser(int userId) throws UserNotFoundException {
        if (userId < 1) throw new UserNotFoundException();

        userDao.deleteUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    private boolean verifyUserNotNull(User user)
    {
        if (user.getCurrentBalance() == null ||
            VerificationHelper.isStringInvalid(user.getEmail()) ||
            VerificationHelper.isStringInvalid(user.getFirstName()) ||
            VerificationHelper.isStringInvalid(user.getLastName()) ||
            VerificationHelper.isStringInvalid(user.getPassword()))
                return false;
        return true;
    }
}
