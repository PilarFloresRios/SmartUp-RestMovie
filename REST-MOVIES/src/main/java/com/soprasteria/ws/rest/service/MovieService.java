package com.soprasteria.ws.rest.service;

import java.util.List;

import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.utils.exceptions.ListIsEmptyException;
import com.soprasteria.ws.rest.utils.exceptions.MovieExistsException;

public interface MovieService {
	
//	Las interfaces no pueden tener constructores
//	Son metodos abstractos que se impletentaran posteriormente en otras clases que implementan de esta interface
	
	public abstract MovieResponseFull getMovieId(Long id);
	public abstract MovieResponseFull getMovieByTitle(String title);
	public abstract List<MovieResponseFull> getMovieByGenre(String genre);
	public abstract MovieResponseFull save(MovieRequest movie) throws MovieExistsException;
	public abstract MovieResponseFull update(MovieRequest movie, Long id);
	public abstract String delete (long id);
	public abstract List<MovieResponse> getMovieList() throws ListIsEmptyException;
	public abstract List<MovieResponseFull> seachMovie(String title, String genre, String year) throws ListIsEmptyException;
}
