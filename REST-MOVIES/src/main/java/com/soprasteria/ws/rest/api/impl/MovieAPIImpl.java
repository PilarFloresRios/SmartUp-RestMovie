package com.soprasteria.ws.rest.api.impl;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.ws.rest.api.MovieAPI;
import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.service.MovieService;
import com.soprasteria.ws.rest.utils.exceptions.ListIsEmptyException;
import com.soprasteria.ws.rest.utils.exceptions.MovieExistsException;

@RestController
public class MovieAPIImpl implements MovieAPI {

	MovieService movieService;

	@Autowired
	public MovieAPIImpl(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@Override
	@GetMapping(value = "/movies/{id}")
	public MovieResponseFull getById(@PathVariable Long id) {

		return movieService.getMovieId(id);
	}

	@Override
	@GetMapping(value = "/movies/title/{title}")
	public MovieResponseFull getMovieByTitle(@PathVariable String title) {

		return movieService.getMovieByTitle(title);
	}
	
	@Override
	@GetMapping(value = "/movies/genre/{genre}")
	public List<MovieResponseFull> getMovieByGenre(@PathVariable String genre) {

		return movieService.getMovieByGenre(genre);
	}

	@Override
	@GetMapping(value = "/movies")
	public List<MovieResponse> getMovieList() throws ListIsEmptyException {

		return movieService.getMovieList();
	}

	@PostMapping(value = "/movies")
	public MovieResponseFull save(@Valid @RequestBody MovieRequest movieRequest) throws MovieExistsException {

		return movieService.save(movieRequest);
	}
	
	@PutMapping(value = "/movies/{id}")
	public MovieResponseFull update(@Valid @RequestBody MovieRequest movieRequest, @PathVariable Long id) {

		return movieService.update(movieRequest, id);
	}

	@DeleteMapping(value = "/movies/{id}")
	public String delete(@PathVariable Long id) {
		return movieService.delete(id);
	}
	

	@GetMapping(value = "/movies/search")
	public List<MovieResponseFull> findMovie(@QueryParam(value = "title") String title, 
											 @QueryParam(value = "genre") String genre,
											 @QueryParam(value = "year") String year) throws ListIsEmptyException {

		return movieService.seachMovie(title, genre, year);
	}
	

}
