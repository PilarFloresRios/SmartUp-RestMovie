package com.soprasteria.ws.rest.utils.builders.concrete;

import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.entity.MovieEntity;

public class MovieBuilderObject {

	private Long id;

	private String title;

	private String genre;

	private int year;

	private String[] actors;

	
	
	
	
	public MovieBuilderObject(Long id, String title, String genre, int year, String[] actors) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.actors = actors;
	}


	public MovieEntity getResultEntity() {

		MovieEntity generic = new MovieEntity();
		generic.setTitle(title);
		generic.setGenre(genre);
		generic.setYear(year);
		generic.setActors(actors);
		

		return generic;
	}

	
	public MovieResponse getResultResponse() {

		MovieResponse generic = new MovieResponse();
		generic.setId(id);
		generic.setTitle(title);
		generic.setYear(year);
		

		return generic;
	}
	
	public MovieResponseFull getResultResponseFull() {

		MovieResponseFull generic = new MovieResponseFull();
		generic.setId(id);
		generic.setTitle(title);
		generic.setGenre(genre);
		generic.setYear(year);
		generic.setActors(actors);

		return generic;
	}


}
