package kr.mjc.kys.spring.jdbc.user;

import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import kr.mjc.kys.spring.jdbc.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ListUsers {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);
        log.info(userDao.listUsers(10, 1).toString());
    }
}
