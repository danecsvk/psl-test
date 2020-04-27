package sk.stuba.fiit.psl.test.controller;

import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.stuba.fiit.psl.test.dao.MovieDao;
import sk.stuba.fiit.psl.test.data.Movie;

@Controller
public class MovieController {

	public static int NUMBER_OF_MEASUREMENTS = 10;

	private final Jdbi jdbi;
	private final MovieDao movieDao;

	MovieController(Jdbi jdbi) {
		this.jdbi = jdbi;
		this.movieDao = new MovieDao();
	}

	@GetMapping("/movies")
	@ResponseBody
	public ResponseEntity<Object> getAllMovies() throws Exception {
		try (Handle h = this.jdbi.open()) {
			List<Movie> movies = this.movieDao.getAllMovies(h);
			return new ResponseEntity<>(movies, HttpStatus.OK);
		}
	}

	@GetMapping("/movies/{id}")
	@ResponseBody
	public ResponseEntity<Object> getMovieById(@PathVariable(value = "id") int id) throws Exception {
		try (Handle h = this.jdbi.open()) {
			Movie movie = this.movieDao.getMovieById(h, id);
			return new ResponseEntity<>(movie, HttpStatus.OK);
		}
	}
}
