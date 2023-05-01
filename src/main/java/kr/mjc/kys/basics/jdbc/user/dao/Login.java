package kr.mjc.kys.basics.jdbc.user.dao;

import kr.mjc.kys.basics.jdbc.DaoFactory;
import kr.mjc.kys.basics.jdbc.user.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Login {
    public static void main(String[] args) {
        UserDao userDao = DaoFactory.getUserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Login - email password : ");
        String[] params = {scanner.next(), scanner.next()};
        scanner.close();

        User user = userDao.login(params[0], params[1]);
        if (user != null)
            log.debug(user.toString());
        else
            log.debug("로그인 실패");
    }
}
