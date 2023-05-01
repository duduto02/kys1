package kr.mjc.kys.spring.jdbc.dao;

import kr.mjc.kys.basics.jdbc.user.User;
import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Component
public class UserDaoUsingNamedParameterJdbcTemplate implements UserDao {

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
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // injection을 위한 constructor
    public UserDaoUsingNamedParameterJdbcTemplate(JdbcTemplate jdbcTemplate,
                                                  NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // resultSet를 user에 자동 매핑하는 매퍼
    RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

    /* 회원 목록
     * param count     목록의 갯수
     * param page      페이지
     * return 회원 목록
     * */

    @Override
    public List<User> listUsers(int count, int page) {
        int offset = (page - 1) * count;
        return jdbcTemplate.query(LIST_USERS, userRowMapper, offset, count);
    }

    /* 회원정보 1건 조회
     * param userId
     * return 회원 정보
     * */
    @Override
    public User getUser(int userId) {
        return jdbcTemplate.queryForObject(GET_USER, userRowMapper, userId);
    }

    /* 로그인
     * param email     이메일
     * param password  비밀번호
     * return 로그인 성공 = 회원정보, 실패 = NULL
     *  */

    @Override
    public User login(String email, String password) {
        return jdbcTemplate.queryForObject(LOGIN, userRowMapper, email, password);
    }

    /* 회원 가입
     * param user   회원정보
     * throws DataAccessException 이메일 중복
     * */
    @Override
    public void addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(ADD_USER, params, keyHolder);
        user.setUserId(keyHolder.getKey().intValue());
    }

    /* 비밀번호 변경
     * param userId          회원번호
     * param currentPassword 현재 비밀번호
     * param newPassword     새 비밀번호
     * return 수정 성공 = 1, 성공 외에 = 0
     * */
    @Override
    public int updatePassword(int userId, String currentPassword, String newPassword) {
        return jdbcTemplate.update(UPDATE_PASSWORD, newPassword, userId, currentPassword);
    }

    /* 회원 삭제
     * param userId    회원번호
     * param password  비밀번호
     * return 삭제 성공 = 1, 성공 외에 = 0
     * */
    @Override
    public int deleteUser(int userId, String password) {
        return jdbcTemplate.update(DELETE_USER, userId, password);
    }
}
