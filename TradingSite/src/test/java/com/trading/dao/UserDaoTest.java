package com.trading.dao;

import com.trading.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class UserDaoTest {

//    UserDao userDao;
//
//    public UserDaoTest()
//    {
//        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        userDao = ctx.getBean("userDao", UserDao.class);
//    }
//
//    @Before
//    public void setUp()
//    {
//        List<User> users = userDao.getAllUsers();
//        for (User currentUser : users)
//        {
//            userDao.deleteUser(currentUser.getUserId());
//        }
//    }
//
//    @Test
//    public void testAddGetUser()
//    {
//        User user = PowerMockito.mock(User.class);
//
//        userDao.addUser(user);
//
//        User retrievedUser = userDao.getUser(user.getUserId());
//
//        Assert.assertEquals(user, retrievedUser);
//    }
//
//    @Test
//    public void testDeleteUser()
//    {
//        User user = PowerMockito.mock(User.class);
//        userDao.addUser(user);
//
//        Assert.assertEquals(1, userDao.getAllUsers().size());
//
//        userDao.deleteUser(user.getUserId());
//
//        Assert.assertEquals(0, userDao.getAllUsers().size());
//    }
//
//    @Test
//    public void testEditUser()
//    {
//        User user = generateUserGeneric();
//        userDao.addUser(user);
//        User retrievedOriginalUser = userDao.getUser(user.getUserId());
//
//        Assert.assertEquals(user, retrievedOriginalUser);
//
//        user.setEmail("newemail@gmail.com");
//        userDao.editUser(user);
//        User retrievedEditedUser = userDao.getUser(user.getUserId());
//
//        Assert.assertEquals(user, retrievedEditedUser);
//        Assert.assertNotEquals(retrievedOriginalUser, retrievedEditedUser);
//    }
//
//    @Test
//    public void testGetAllUsers()
//    {
//        User firstUser = generateUser(1);
//        User secondUser = generateUser(2);
//
//        userDao.addUser(firstUser);
//        userDao.addUser(secondUser);
//
//        Assert.assertEquals(2, userDao.getAllUsers().size());
//    }
//
//    private User generateUser(int userId)
//    {
//        return new User("John", "Doe", "abc@gmail.com",
//                new BigDecimal(5000), "password");
//    }
//
//    private User generateUserGeneric()
//    {
//        return generateUser(1);
//    }

}