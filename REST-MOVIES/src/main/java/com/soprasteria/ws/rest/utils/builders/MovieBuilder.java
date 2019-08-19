package com.soprasteria.ws.rest.utils.builders;

import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.entity.MovieEntity;
import com.soprasteria.ws.rest.utils.builders.concrete.MovieBuilderObject;

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
