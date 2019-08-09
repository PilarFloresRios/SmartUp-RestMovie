package com.soprasteria.ws.rest.builder;

import com.soprasteria.ws.rest.builder.concrete.MovieBuilderObject;
import com.soprasteria.ws.rest.entity.MovieEntity;
import com.soprasteria.ws.rest.request.movie.MovieRequest;
import com.soprasteria.ws.rest.response.movie.MovieResponse;
import com.soprasteria.ws.rest.response.movie.MovieResponseFull;

public class MovieBuilder {

	public MovieResponse toMovieResponse(MovieEntity movie) {

		MovieBuilderObject builder = new MovieBuilderObject(movie.getId(), movie.getTitle(), null, movie.getYear(), null);

		return builder.getResultResponse();

	}

	public MovieResponseFull toMovieResponseFull(MovieEntity movie) {

		MovieBuilderObject builder = new MovieBuilderObject(movie.getId(), movie.getTitle(), movie.getGenre(), movie.getYear(), movie.getActors());

		return builder.getResultResponseFull();

	}

	public MovieEntity toMovieEntity(MovieRequest movie) {

		MovieBuilderObject builder = new MovieBuilderObject(null, movie.getTitle(), movie.getGenre(), movie.getYear(), movie.getActors());

		return builder.getResultEntity();

	}


}
