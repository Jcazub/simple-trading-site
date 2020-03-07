package com.trading.dao.DatabaseImpl;

import com.trading.dao.UserDao;
import com.trading.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoDatabaseImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public UserDaoDatabaseImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String INSERT_USER = "insert into db.users (firstName, lastName, email, currentBalance, password) values (?,?,?,?,?)";
    private static final String DELETE_USER = "delete from db.users where userId = ?";
    private static final String EDIT_USER = "update db.users set firstName = ?, lastName = ?, email = ?," +
            "currentBalance = ?, password = ? where userId = ?";
    private static final String SELECT_USER = "select * from db.users where userId = ?";
    private static final String SELECT_ALL_USERS = "select * from db.users";

    private static final String INSERT_USER_ROLE = "insert into db.users_roles (userId, roleType) values (?,?)";
    private static String ROLE_USER = "USER";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User addUser(User user) {
        SqlParameterSource sps = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("currentBalance", user.getCurrentBalance().doubleValue())
                .addValue("password", user.getPassword());

//        jdbcTemplate.update(INSERT_USER, user.getFirstName(), user.getLastName(), user.getEmail(),
//                user.getCurrentBalance().doubleValue(), user.getPassword());

        int userId = new SimpleJdbcInsert(dataSource)
                .withTableName("db.users")
                .usingGeneratedKeyColumns("userId")
                .executeAndReturnKey(sps)
                .intValue();

                //jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setUserId(userId);
        addUserRole(userId);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User editUser(User user) {
        jdbcTemplate.update(EDIT_USER, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getCurrentBalance().doubleValue(), user.getPassword(), user.getUserId());
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getUser(int userId) {
        return jdbcTemplate.queryForObject(SELECT_USER, new TradingMappers.UserMapper(), userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(int userId) {
        jdbcTemplate.update(DELETE_USER, userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_USERS, new TradingMappers.UserMapper());
    }

    private void addUserRole(int userId) {
        jdbcTemplate.update(INSERT_USER_ROLE, userId, ROLE_USER);
    }
}
