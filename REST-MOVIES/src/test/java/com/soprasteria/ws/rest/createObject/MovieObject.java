package com.soprasteria.ws.rest.createObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.entity.MovieEntity;

public class MovieObject {
	
	
	

	@Autowired
	public MovieObject(Long id) {
		super();
//		createMovieEntity(id);
	}
	
	


	public static MovieEntity createMovieEntity(Long id) {
		
		MovieEntity movie = new MovieEntity();
		
		movie.setId(id);	
	
		return movie;
	}
	
	
	public static MovieRequest createMovieRequest() {
		
		MovieRequest movie = new MovieRequest();

		
		movie.setTitle("Titulo test");
	
		
		return movie;
	}
	
	public static MovieResponse createMovieResponse(Long id) {
		
		MovieResponse movie =  new MovieResponse();
		movie.setId(id);
		
		return movie;
	}
	
	public static MovieResponseFull createMovieResponseFull(Long id) {
		
		MovieResponseFull movie =  new MovieResponseFull();
		movie.setId(id);
		
		return movie;
	}
	
//	public static MovieResponseFull convertEntityToResponseFull(Long id, MovieEntity movieEntity) {
//		MovieResponseFull movieResponseFull = MovieObject.createMovieResponseFull(id);
////		when(movieEntity.toMovieResponseFull()).thenReturn(movieResponseFull);
//		
//		return movieResponseFull;
//	}
//	
//	public static MovieEntity convertMovieRequestToEntity(Long id, MovieRequest movieIn) {
//		MovieEntity movieOut = MovieObject.createMovieEntity(id);
//		when(builder.).thenReturn(movieOut);
//		
//		return movieOut;
//	}

}
