package com.soprasteria.ws.rest.api;

import java.util.List;

import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.utils.exceptions.ListIsEmptyException;
import com.soprasteria.ws.rest.utils.exceptions.MovieExistsException;

public interface MovieAPI {

	public abstract MovieResponseFull getById(Long id);
	
	public abstract MovieResponseFull getMovieByTitle(String title);
	
	public abstract List<MovieResponseFull> getMovieByGenre(String genre);
	
	public abstract List<MovieResponse> getMovieList() throws ListIsEmptyException;
	
	public abstract MovieResponseFull save(MovieRequest movieRequest) throws MovieExistsException;

	public abstract MovieResponseFull update(MovieRequest movieReques, Long id);

	public abstract StringBuffer delete (Long id);
	
	public abstract List<MovieResponseFull> findMovie(String title, String genre, String year) throws ListIsEmptyException;
}
