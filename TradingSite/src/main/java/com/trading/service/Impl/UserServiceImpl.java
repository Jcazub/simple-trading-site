package com.trading.service.Impl;

import com.trading.dao.UserDao;
import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.model.User;
import com.trading.service.UserService;
import com.trading.utilites.VerificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
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
