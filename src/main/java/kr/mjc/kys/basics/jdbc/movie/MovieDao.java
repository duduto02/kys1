package kr.mjc.kys.basics.jdbc.movie;

import java.util.List;

public interface MovieDao {
    List<Movie> listMovies(int count, int page);

    void addMovie(Movie movie);
}
