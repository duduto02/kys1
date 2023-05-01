package kr.mjc.kys.basics.jdbc.movie;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class ListMovies {
    public static void main(String[] args) {
        MovieDao movieDao = new MovieDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("List - count page : ");
        int[] params = {scanner.nextInt(),scanner.nextInt()};
        scanner.close();

        List<Movie> movieList = movieDao.listMovies(params[0],params[1]);
        log.debug(movieList.toString());
    }
}
