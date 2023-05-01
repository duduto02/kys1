package kr.mjc.kys.spring.jdbc.user;

import kr.mjc.kys.basics.jdbc.user.dao.UserDao;
import kr.mjc.kys.spring.jdbc.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@Slf4j
public class UpdatePassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Update password - userId currentPassword newPassword : ");
        int userId = scanner.nextInt();
        String currentPassword = scanner.next();
        String newPassword = scanner.next();
        scanner.close();

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);

        int updateRows =
                userDao.updatePassword(userId, currentPassword, newPassword);
        if (updateRows >= 1)
            log.info("수정 성공");
        else
            log.info("수정 실패");
    }
}
