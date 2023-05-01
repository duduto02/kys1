package kr.mjc.kys.basics.jdbc.user.raw;

import kr.mjc.kys.basics.jdbc.DataSourceFactory;
import kr.mjc.kys.basics.jdbc.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Slf4j
public class ListUsers {
    public static void main(String[] args) {
        DataSource ds = DataSourceFactory.getDataSource();
        Scanner scanner = new Scanner(System.in);
        System.out.print("List - count page : ");

        int count = scanner.nextInt();
        int page = scanner.nextInt();
        scanner.close();

        int offset = (page - 1) * count;
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "select userId, email, name from user order by userId desc limit ?,?")) {
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
            log.debug(userList.toString());
        } catch (SQLException e) {
            log.error(e.toString());
        }
    }
}
