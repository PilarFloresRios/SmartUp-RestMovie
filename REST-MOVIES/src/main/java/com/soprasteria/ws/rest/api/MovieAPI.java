package com.soprasteria.ws.rest.api;

import java.util.List;

import com.soprasteria.ws.rest.exception.ListIsEmptyException;
import com.soprasteria.ws.rest.exception.MovieExistsException;
import com.soprasteria.ws.rest.request.movie.MovieRequest;
import com.soprasteria.ws.rest.response.movie.MovieResponse;
import com.soprasteria.ws.rest.response.movie.MovieResponseFull;

public interface MovieAPI {

	public abstract MovieResponseFull getById(Long id);
	
	public abstract MovieResponseFull getMovieByTitle(String title);
	
	public abstract MovieResponseFull getMovieByGenre(String genre);
	
	public abstract List<MovieResponse> getMovieList() throws ListIsEmptyException;
	
	public abstract MovieResponseFull save(MovieRequest movieRequest) throws MovieExistsException;

	public abstract MovieResponseFull update(MovieRequest movieReques, Long id);

	public abstract StringBuffer delete (Long id);
}
