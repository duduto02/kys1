package kr.mjc.kys.spring.di.quiz;

import kr.mjc.kys.basics.jdbc.DataSourceFactory;
import kr.mjc.kys.basics.jdbc.JdbcHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
 * dataSource와 jdbcHelper는 @Bean으로 구성
 * userDao는 @ComponentScan과 @Component로 구성
 * */
@Configuration
@ComponentScan(basePackages = "kr.mjc.kys.spring.di.quiz")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceFactory.getDataSource();
    }

    @Bean
    public JdbcHelper jdbcHelper(DataSource dataSource) {
        return new JdbcHelper(dataSource);
    }
}
