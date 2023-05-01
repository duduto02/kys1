package kr.mjc.kys.spring.di.quiz;

import kr.mjc.kys.basics.jdbc.user.User;
import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@Slf4j
public class ListUsers {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        List<User> userList = userDao.listUsers(5, 1);
        log.info(userList.toString());
    }
}
