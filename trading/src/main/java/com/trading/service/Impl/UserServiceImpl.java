package com.trading.service.Impl;

import com.trading.dao.UserDao;
import com.trading.exceptions.EmailAlreadyInUseException;
import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.model.User;
import com.trading.service.UserService;
import com.trading.utilites.VerificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    public User addUser(User user) throws MalformedObjectException, EmailAlreadyInUseException {
        if (isUserMalformed(user)) throw new MalformedObjectException();
        return userDao.addUser(user);
    }

    @Override
    public User editUser(User user) throws MalformedObjectException, UserNotFoundException {
        if (isUserMalformed(user)) throw new MalformedObjectException();
        else if (user.getUserId() < 1) throw new UserNotFoundException();
        return userDao.editUser(user);
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException {
        if (userId < 1) throw new UserNotFoundException();
        return userDao.getUser(userId);
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        if (VerificationHelper.isStringInvalid(email)) throw new UserNotFoundException();
        return userDao.getUserByEmail(email);
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

    private boolean isUserMalformed(User user)
    {
        if (user.getCurrentBalance() == null ||
            VerificationHelper.isStringInvalid(user.getEmail()) ||
            VerificationHelper.isStringInvalid(user.getFirstName()) ||
            VerificationHelper.isStringInvalid(user.getLastName()) ||
            VerificationHelper.isStringInvalid(user.getPassword()))
                return true;
        return false;
    }
}
