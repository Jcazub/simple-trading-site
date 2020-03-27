package com.trading.dao;

import com.trading.BaseInitializer;
import com.trading.SpringConfig;
import com.trading.model.User;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfig.class}, initializers = {UserDaoTest.Initializer.class},
        loader = AnnotationConfigWebContextLoader.class)
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @ClassRule
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>()
            .withUsername("root")
            .withPassword("")
            .withInitScript("create-database.sql");

    @Before
    public void setUp()
    {
        List<User> users = userDao.getAllUsers();
        for (User currentUser : users)
        {
            userDao.deleteUser(currentUser.getUserId());
        }
    }

    @Test
    public void testAddGetUser()
    {
//        User user = PowerMockito.mock(User.class);
//
//        userDao.addUser(user);
//
//        User retrievedUser = userDao.getUser(user.getUserId());
//
//        Assert.assertEquals(user, retrievedUser);
    }

    public static class Initializer
            extends BaseInitializer {
        public Initializer() {
            super(mySQLContainer);
        }
    }

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