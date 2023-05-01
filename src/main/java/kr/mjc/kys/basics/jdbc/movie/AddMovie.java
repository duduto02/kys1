package kr.mjc.kys.basics.jdbc.movie;

import kr.mjc.kys.basics.jdbc.DbException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@Slf4j
public class AddMovie {
    public static void main(String[]args) {
        MovieDao movieDao = new MovieDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert - title director : ");
        Movie movie = new Movie();
        movie.setTitle(scanner.next());
        movie.setDirector(scanner.next());
        scanner.close();

        try {
            movieDao.addMovie(movie);
            log.debug(movie.toString());
        } catch (DbException e) {
            log.error(e.toString());
        }
    }
}
