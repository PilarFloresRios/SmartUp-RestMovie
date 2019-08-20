package com.soprasteria.ws.rest.dto.request.movie;

import java.util.Arrays;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.soprasteria.ws.rest.utils.annotations.Genre;
import com.soprasteria.ws.rest.utils.annotations.Year;

public class MovieRequest {

	@NotBlank(message = "{movie.title.notBlank}")
	@Size(min = 4, message = "The title must have a minimum of 4 characters")
	private String title;
	@NotNull (message = "{movie.genre.notNull}")
	@Genre
	private String genre;
	@Year
	private int year;
	private String actors[] = null;

	


	public MovieRequest() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}





	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieRequest [title=");
		builder.append(title);
		builder.append(", genre=");
		builder.append(genre);
		builder.append(", year=");
		builder.append(year);
		builder.append(", actors=");
		builder.append(Arrays.toString(actors));
		builder.append("]");
		return builder.toString();
	}

}
