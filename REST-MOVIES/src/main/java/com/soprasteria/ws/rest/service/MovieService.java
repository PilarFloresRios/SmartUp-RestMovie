package com.soprasteria.ws.rest.service;

import java.util.List;

import com.soprasteria.ws.rest.exception.ListIsEmptyException;
import com.soprasteria.ws.rest.exception.MovieExistsException;
import com.soprasteria.ws.rest.request.movie.MovieRequest;
import com.soprasteria.ws.rest.response.movie.MovieResponse;
import com.soprasteria.ws.rest.response.movie.MovieResponseFull;

public interface MovieService {
	
//	Las interfaces no pueden tener constructores
//	Son metodos abstractos que se impletentaran posteriormente
	
	public abstract MovieResponseFull getMovieId(Long id);
	public abstract MovieResponseFull getMovieByTitle(String title);
	public abstract MovieResponseFull getMovieByGenre(String genre);
	public abstract MovieResponseFull save(MovieRequest movie) throws MovieExistsException;
	public abstract MovieResponseFull update(MovieRequest movie, Long id);
	public abstract StringBuffer delete (long id);
	public abstract List<MovieResponse> getMovieList() throws ListIsEmptyException;
}
