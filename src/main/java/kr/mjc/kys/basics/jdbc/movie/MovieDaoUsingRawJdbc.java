package kr.mjc.kys.basics.jdbc.movie;

import kr.mjc.kys.basics.jdbc.DataSourceFactory;
import kr.mjc.kys.basics.jdbc.DbException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MovieDaoUsingRawJdbc implements MovieDao {
    private static final String LIST_MOVIES =
            "select movieId, title, director from movie order by movieId desc limit ?,?";
    private static final String ADD_MOVIE =
            "insert movie(title, director) values(?,?)";
    private final DataSource ds;
    public MovieDaoUsingRawJdbc() {
        ds = DataSourceFactory.getDataSource(); }

    @Override
    public List<Movie> listMovies(int count, int page) {
        int offset = (page - 1) * count;
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(LIST_MOVIES)) {
            ps.setInt(1, offset);
            ps.setInt(2,page);
            ResultSet rs = ps.executeQuery();

            List<Movie> movieList = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movieId"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movieList.add(movie);
            } return movieList;
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void addMovie(Movie movie) throws DbException {
        try (Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(ADD_MOVIE,
                    Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDirector());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                movie.setMovieId(rs.getInt("insert_id"));
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
