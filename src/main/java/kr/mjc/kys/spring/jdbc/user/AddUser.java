package kr.mjc.kys.spring.jdbc.user;

import kr.mjc.kys.basics.jdbc.user.User;
import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import kr.mjc.kys.spring.jdbc.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Scanner;

@Slf4j
public class AddUser {
    public static void main(String[] args) {
        System.out.print("Input email password name : ");
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        user.setEmail(scanner.next());
        user.setPassword(scanner.next());
        user.setName(scanner.next());
        scanner.close();

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);
        try {
            userDao.addUser(user);
            log.info("입력 완료, {}", user);
        } catch (DataAccessException e) {
            log.info("이메일 중복. {}", e.getCause().toString());
        }
    }
}
