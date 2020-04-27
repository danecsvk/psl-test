package sk.stuba.fiit.psl.test.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class Movie {

	private String name;
	private int year;
	private String description;

	public Movie() {

	}

	public Movie(String name, int year, String description) {
		this.name = name;
		this.year = year;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static class MovieMapper implements RowMapper<Movie> {

		@Override
		public Movie map(ResultSet r, StatementContext ctx) throws SQLException {
			Movie result = new Movie();
			result.name = r.getString("name");
			result.year = r.getInt("year");
			result.description = r.getString("description");
			return result;
		}
	}
}
