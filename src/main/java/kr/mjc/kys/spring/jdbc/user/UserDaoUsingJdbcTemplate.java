package kr.mjc.kys.spring.jdbc.user;

import kr.mjc.kys.basics.jdbc.user.User;
import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoUsingJdbcTemplate implements UserDao {
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

    private final JdbcTemplate jdbcTemplate;

    public UserDaoUsingJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* 자동 매핑
     * RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);
     */

    private User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        return user;
    }

    @Override
    public List<User> listUsers(int count, int page) {
        int offset = (page - 1) * count;
        return jdbcTemplate.query(LIST_USERS, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            return user;
        }, offset, count);
    }

    @Override
    public User getUser(int userId) {
        return jdbcTemplate.queryForObject(GET_USER, this::mapRow, userId);
    }

    @Override
    public User login(String email, String password) {
        return jdbcTemplate.queryForObject(LOGIN, this::mapRow, email, password);
    }

    @Override
    public void addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_USER,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            return ps;
        }, keyHolder);
        user.setUserId(keyHolder.getKey().intValue());
    }

    @Override
    public int updatePassword(int userId, String currentPassword, String newPassword) {
        return jdbcTemplate.update(UPDATE_PASSWORD, newPassword, userId, currentPassword);
    }

    @Override
    public int deleteUser(int userId, String password) {
        return jdbcTemplate.update(DELETE_USER, userId, password);
    }
}
