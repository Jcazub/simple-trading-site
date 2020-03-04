package dao.DatabaseImpl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoDatabaseImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoDatabaseImpl(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String INSERT_USER = "insert into db.users (firstName, lastName, email, currentBalance, password) values (?,?,?,?,?)";
    private static final String DELETE_USER = "delete from db.users where userId = ?";
    private static final String EDIT_USER = "update db.users set firstName = ?, lastName = ?, email = ?," +
            "currentBalance = ?, password = ? where userId = ?";
    private static final String SELECT_USER = "select * from db.users where userId = ?";
    private static final String SELECT_ALL_USERS = "select * from db.users";

    private static final String INSERT_USER_ROLE = "insert into db.users_roles (userId, roleType) values (?,?)";
    private static String ROLE_USER = "ROLE_USER";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {
        jdbcTemplate.update(INSERT_USER, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getCurrentBalance().doubleValue(), user.getPassword());
        int userId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setUserId(userId);
        addUserRole(userId);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User editUser(User user) {
        jdbcTemplate.update(EDIT_USER, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getCurrentBalance().doubleValue(), user.getPassword(), user.getUserId());
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User getUser(int userId) {
        return jdbcTemplate.queryForObject(SELECT_USER, new TradingMappers.UserMapper(), userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(int userId) {
        jdbcTemplate.update(DELETE_USER, userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_USERS, new TradingMappers.UserMapper());
    }

    private void addUserRole(int userId) {
        jdbcTemplate.update(INSERT_USER_ROLE, userId, ROLE_USER);
    }
}
