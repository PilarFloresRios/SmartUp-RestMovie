package com.soprasteria.ws.rest.api.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.ws.rest.api.MovieAPI;
import com.soprasteria.ws.rest.exception.ListIsEmptyException;
import com.soprasteria.ws.rest.exception.MovieExistsException;
import com.soprasteria.ws.rest.request.movie.MovieRequest;
import com.soprasteria.ws.rest.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.response.movie.MovieResponse;
import com.soprasteria.ws.rest.service.MovieService;

@RestController
public class MovieAPIImpl implements MovieAPI {

	MovieService movieService;

	@Autowired
	public MovieAPIImpl(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@Override
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
	public MovieResponseFull getById(@PathVariable Long id) {

		return movieService.getMovieId(id);
	}

	@Override
	@RequestMapping(value = "/movie/title/{title}", method = RequestMethod.GET)
	public MovieResponseFull getMovieByTitle(@PathVariable String title) {

		return movieService.getMovieByTitle(title);
	}
	
	@Override
	@RequestMapping(value = "/movie/genre/{genre}", method = RequestMethod.GET)
	public MovieResponseFull getMovieByGenre(@PathVariable String genre) {

		return movieService.getMovieByTitle(genre);
	}

	@Override
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public List<MovieResponse> getMovieList() throws ListIsEmptyException {

		return movieService.getMovieList();
	}

	@RequestMapping(value = "/movie", method = RequestMethod.POST)
	public MovieResponseFull save(@Valid @RequestBody MovieRequest movieRequest) throws MovieExistsException {

		return movieService.save(movieRequest);
	}
	
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
	public MovieResponseFull update(@Valid @RequestBody MovieRequest movieRequest, @PathVariable Long id) {

		return movieService.update(movieRequest, id);
	}

	@RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
	public StringBuffer delete(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return movieService.delete(id);
	}
	

}
