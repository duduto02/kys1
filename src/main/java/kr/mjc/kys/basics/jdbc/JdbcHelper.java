package kr.mjc.kys.basics.jdbc;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JdbcHelper {
    private final DataSource dataSource;

    public JdbcHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> list(String sql, ResultMapper<T> mapper,
                            Object... params) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParameters(ps, params);
            ResultSet rs = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T get(String sql, ResultMapper<T> mapper, Object... params) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParameters(ps, params);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return mapper.mapRow(rs);
            else
                return null;
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    public int update(String sql, Object... params) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParameters(ps, params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    public int insert(String sql, SingleKeyHolder keyHolder, Object... params) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            setParameters(ps, params);
            int updateRows = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                keyHolder.setKey(rs.getInt(1));
            rs.close();

            return updateRows;
        } catch (SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    private void setParameters(PreparedStatement ps, Object... params)
            throws SQLException {
        if (params != null) {
            int i = 1;
            for (Object param : params)
                ps.setObject(i++, param);
        }
    }
}
