package com.soprasteria.ws.rest.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.soprasteria.ws.rest.builder.MovieBuilder;
import com.soprasteria.ws.rest.createObject.MovieObject;
import com.soprasteria.ws.rest.dao.MovieDAO;
import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.entity.MovieEntity;
import com.soprasteria.ws.rest.service.impl.MovieServiceImpl;
import com.soprasteria.ws.rest.utils.exceptions.ListIsEmptyException;
import com.soprasteria.ws.rest.utils.exceptions.MovieExistsException;
import com.soprasteria.ws.rest.utils.exceptions.OrderNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

	private MovieService movieService;

	@Mock
	private MovieDAO movieDAO;
	
	@Mock 
	private MovieBuilder builder;

	@Before
	public void setUp() throws Exception {

		movieService = new MovieServiceImpl(movieDAO, builder);

	}

	@Test(expected = OrderNotFoundException.class)
	public void whenNotExitMovieID_thenOrderNotFoundException() {

		Long id = 1L;
		Mockito.when(movieDAO.findById(id)).thenReturn(Optional.empty());

		MovieResponseFull actual = movieService.getMovieId(id);

	}

	@Test
	public void whenExistMovieID_thenMovieShouldBeFound() {

		Long id = 1L;
		MovieEntity dto = MovieObject.createMovieEntity(id);
		when(movieDAO.findById(id)).thenReturn(Optional.of(dto));

		MovieResponseFull movieResponse = MovieObject.createMovieResponseFull(id);
		when(builder.toMovieResponseFull(dto)).thenReturn(movieResponse);

		MovieResponseFull actual = movieService.getMovieId(id);

		assertEquals(id, actual.getId());
	}

	@Test(expected = ListIsEmptyException.class)
	public void whenNotExistList_thenListShouldBeEmpty() throws ListIsEmptyException {

		List<MovieResponse> movieListActuals = movieService.getMovieList();

	}

	@Test
	public void whenExistList_thenListShouldBeShow() throws ListIsEmptyException {

		List<MovieEntity> movieList = new ArrayList<>();
		Long id = 1L;
		MovieEntity movie1 = MovieObject.createMovieEntity(id);
		movieList.add(movie1);
		when(movieDAO.findAll()).thenReturn(movieList);
		
		List<MovieResponse> listExpected = new ArrayList<>();
		MovieResponse movieR1 = MovieObject.createMovieResponse(id);
		listExpected.add(movieR1);

		when(builder.toMovieResponse(movie1)).thenReturn(movieR1);

		List<MovieResponse> listActual = movieService.getMovieList();

		assertEquals(listExpected, listActual);
	}

	@Test(expected = MovieExistsException.class)
	public void whenMovieExists_thenMovieShouldNotBeSaved() throws Throwable {

		MovieRequest movieToSave = MovieObject.createMovieRequest();
		

		MovieEntity movieEntity = MovieObject.createMovieEntity(1L);
//		when(movieDAO.findMovieDTOByTitle(movieToSave.getTitle())).thenReturn(Optional.of(movieEntity));

		MovieResponseFull actual = movieService.save(movieToSave);
	}

	@Test
	public void whenMovieInOk_thenMovieShouldBeSaved() throws MovieExistsException {

		Long id = 1L;
		MovieRequest movieToSave = MovieObject.createMovieRequest();
		when(movieDAO.findMovieDTOByTitleIgnoreCase(movieToSave.getTitle())).thenReturn(Optional.empty());

		MovieEntity movieEntity = MovieObject.createMovieEntity(id);
		when(builder.toMovieEntity(movieToSave)).thenReturn(movieEntity);
//		MovieEntity movieEntity = MovieObject.convertMovieRequestToEntity(1L, movieToSave);

		MovieEntity movieEntitySaved = MovieObject.createMovieEntity(1L);
		when(movieDAO.saveAndFlush(movieEntity)).thenReturn(movieEntitySaved);

//		MovieResponseFull movieResponseFull = MovieObject.convertEntityToResponseFull(1L, movieEntitySaved);

		MovieResponseFull movieResponse = MovieObject.createMovieResponseFull(id);
		when(builder.toMovieResponseFull(movieEntitySaved)).thenReturn(movieResponse);
		
		MovieResponseFull actual = movieService.save(movieToSave);

		assertEquals(id, actual.getId());

	}

	@Test
	public void whenExistMovieTitle_thenMovieShouldBeFound() {

		Long id = 1L;
		
		String titleIn = "Titulo de prueba";
		MovieEntity dto = MovieObject.createMovieEntity(1L);
//		when(movieDAO.findMovieDTOByTitle(titleIn)).thenReturn(Optional.of(dto));
		
		MovieResponseFull movieResponseFull = MovieObject.createMovieResponseFull(id);
		when(builder.toMovieResponseFull(dto)).thenReturn(movieResponseFull);


//		MovieResponseFull actual = movieService.getMovieByTitle(titleIn);

//		Assert.assertEquals(id, actual.getId());
	}

	@Test(expected = OrderNotFoundException.class)
	public void whenNotExitMovieByTitle_thenOrderNotFoundException() {

		String titleIn = "Titulo de prueba";
		Mockito.when(movieDAO.findMovieDTOByTitleIgnoreCase(titleIn)).thenReturn(Optional.empty());

//		MovieResponseFull actual = movieService.getMovieByTitle(titleIn);

	}

}
