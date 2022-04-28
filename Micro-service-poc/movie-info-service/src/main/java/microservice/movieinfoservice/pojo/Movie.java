package microservice.movieinfoservice.pojo;

public class Movie {

	private String movieId;

	private String movieName;

	private String description;

	public Movie(String movieId, String movieName, String description) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.description = description;
	}

	public Movie(String movieId, String movieName) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
