package com.soprasteria.ws.rest.response.movie;

import java.util.Arrays;

public class MovieResponseFull extends MovieResponse {

	private String genre;
	private String actors[];

	public MovieResponseFull() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
		builder.append("MovieResponseFull [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", year=");
		builder.append(year);
		builder.append(", genre=");
		builder.append(genre);
		builder.append(", actors=");
		builder.append(Arrays.toString(actors));
		builder.append("]");
		return builder.toString();
	}


	

}
