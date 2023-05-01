package kr.mjc.kys.basics.jdbc.user.dao;

import kr.mjc.kys.basics.jdbc.DaoFactory;
import kr.mjc.kys.basics.jdbc.user.User;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class ListUsers {
    public static void main(String[] args) {
        UserDao userDao = DaoFactory.getUserDao();  // 인터페이스 UserDao 구현체 생성
        Scanner scanner = new Scanner(System.in);
        System.out.print("List - count page : ");
        int[] params = {scanner.nextInt(), scanner.nextInt()};
        scanner.close();

        List<User> userList = userDao.listUsers(params[0], params[1]);
        log.debug(userList.toString());
    }
}
