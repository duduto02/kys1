package kr.mjc.kys.basics.jdbc.user.dao;

import kr.mjc.kys.basics.jdbc.DataSourceFactory;
import kr.mjc.kys.basics.jdbc.user.User;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDaoUsingRawJdbc implements UserDao {
    private static final String LIST_USERS =
            "select userId, email, name from user order by userId desc limit ?,?";
    private static final String GET_USER =
            "select userId, email, name from user where userId=?";
    private static final String LOGIN =
            "select userId, email, name from user where email=? and password=sha2(?,256)";
    private static final String ADD_USER =
            "insert user(email, password, name) values (?,sha2(?,256),?)";
    private static final String UPDATE_PASSWORD =
            "update user set password=sha2(?,256) where userId=? and password=sha2(?,256)";
    private static final String DELETE_USER =
            "delete from user where userId=? and password=sha2(?,256)";
    private DataSource dataSource;

    public UserDaoUsingRawJdbc() {
        dataSource = DataSourceFactory.getDataSource();
    }

    @Override
    public List<User> listUsers(int count, int page) {
        int offset = (page - 1) * count;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(LIST_USERS)) {
            ps.setInt(1, offset);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(int userId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(String email, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(LOGIN)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) throws RuntimeException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                user.setUserId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updatePassword(int userId, String currentPassword, String newPassword) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_PASSWORD)) {
            ps.setObject(1, newPassword);
            ps.setObject(2, userId);
            ps.setObject(3, currentPassword);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteUser(int userId, String password) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_USER)) {
            ps.setInt(1, userId);
            ps.setString(2, password);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
