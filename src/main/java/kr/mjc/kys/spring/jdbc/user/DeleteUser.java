package kr.mjc.kys.spring.jdbc.user;

import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import kr.mjc.kys.spring.jdbc.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@Slf4j
public class DeleteUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Delete - userId password : ");
        int userId = scanner.nextInt();
        String password = scanner.next();
        scanner.close();

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);

        int updateRows = userDao.deleteUser(userId, password);
        if (updateRows >= 1)
            log.info("삭제 완료");
        else
            log.info("삭제 실패");
    }
}
