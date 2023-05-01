package kr.mjc.kys.basics.jdbc;

import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import kr.mjc.kys.basics.jdbc.user.dao.UserDaoUsingJdbcHelper;
import kr.mjc.kys.basics.jdbc.user.dao.UserDaoUsingRawJdbc;

public class DaoFactory {
    public static UserDao getUserDao() {
        return new UserDaoUsingJdbcHelper();
    }
}
