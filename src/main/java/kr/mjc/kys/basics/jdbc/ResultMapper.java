package kr.mjc.kys.basics.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
/* <T> 제네릭에 User, Movie 등 넣어 활용 가능 */