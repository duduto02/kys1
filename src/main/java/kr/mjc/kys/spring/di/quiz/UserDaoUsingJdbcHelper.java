package kr.mjc.kys.spring.di.quiz;

import kr.mjc.kys.basics.jdbc.DataSourceFactory;
import kr.mjc.kys.basics.jdbc.JdbcHelper;
import kr.mjc.kys.basics.jdbc.SingleKeyHolder;
import kr.mjc.kys.basics.jdbc.user.User;
import kr.mjc.kys.basics.jdbc.user.dao.UserDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoUsingJdbcHelper implements UserDao {

    private static final String LIST_USERS =
            "select userId, email, name from user order by userId desc limit ?,?";

    private static final String GET_USER =
            "select userId, email, name from user where userId=?";

    private static final String LOGIN =
            "select userId, email, name from user where email=? and password=sha2(?,256)";

    private static final String ADD_USER =
            "insert user(email, password, name) values(?,sha2(?,256),?)";

    private static final String UPDATE_PASSWORD =
            "update user set password=sha2(?,256) where userId=? and password=sha2(?,256)";

    private static final String DELETE_USER =
            "delete from user where userId=? and password=sha2(?,256)";

    private final JdbcHelper jdbcHelper;

    // constructor - injection
    public UserDaoUsingJdbcHelper(JdbcHelper jdbcHelper) {
        this.jdbcHelper = jdbcHelper;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        return user;
    }

    @Override
    public List<User> listUsers(int count, int page) {
        int offset = (page - 1) * count;
        return jdbcHelper.list(LIST_USERS, this::mapRow, offset, count);
    }

    @Override
    public User getUser(int userId) {
        return jdbcHelper.get(GET_USER, this::mapRow, userId);
    }

    @Override
    public User login(String email, String password) {
        return jdbcHelper.get(LOGIN, this::mapRow, email, password);
    }

    @Override
    public void addUser(User user) throws RuntimeException {
        SingleKeyHolder keyHolder = new SingleKeyHolder();
        jdbcHelper.insert(ADD_USER, keyHolder, user.getEmail(), user.getPassword(), user.getName());
        user.setUserId(keyHolder.getKey());
    }

    @Override
    public int updatePassword(int userId, String currentPassword, String newPassword) {
        return jdbcHelper.update(UPDATE_PASSWORD, newPassword, userId, currentPassword);
    }

    @Override
    public int deleteUser(int userId, String password) {
        return jdbcHelper.update(DELETE_USER, userId, password);
    }
}
