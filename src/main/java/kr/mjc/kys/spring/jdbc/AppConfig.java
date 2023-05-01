package kr.mjc.kys.spring.jdbc;

import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import kr.mjc.kys.spring.jdbc.user.UserDaoUsingJdbcTemplate;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "kr.mjc.kys.spring.jdbc.dao")
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws SQLException {
        return new MariaDbDataSource(env.getProperty("url"));
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /*@Bean
    public UserDaoUsingJdbcTemplate userDaoUsingJdbcTemplate(JdbcTemplate jdbcTemplate) {
        return new UserDaoUsingJdbcTemplate(jdbcTemplate);
    }*/

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}
