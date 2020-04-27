package sk.stuba.fiit.psl.test.dao;

import java.util.List;
import org.jdbi.v3.core.Handle;
import sk.stuba.fiit.psl.test.data.Movie;
import sk.stuba.fiit.psl.test.data.Movie.MovieMapper;

public class MovieDao {

	private static final MovieMapper MOVIE_MAPPER = new MovieMapper();

	private static final String SELECT_MOVIE = ""
			+ " SELECT name, year, description"
			+ " FROM movie";

	public List<Movie> getAllMovies(Handle h) {
		String query = SELECT_MOVIE + " ORDER BY year DESC";
		return h.createQuery(query).map(MOVIE_MAPPER).list();
	}

	public Movie getMovieById(Handle h, int id) {
		String query = SELECT_MOVIE + " WHERE id = :id";
		return h.createQuery(query).bind("id", id).map(MOVIE_MAPPER).first();
	}
}
