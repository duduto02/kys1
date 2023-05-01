package kr.mjc.kys.basics.jdbc.movie;

import lombok.Data;

@Data
public class Movie {
    int movieId;
    String title;
    String director;

    @Override
    public String toString() {
        return String.format("\nMovie{movieId=%s, title=%s, name=%s}",
                movieId, title, director);
    }
}
