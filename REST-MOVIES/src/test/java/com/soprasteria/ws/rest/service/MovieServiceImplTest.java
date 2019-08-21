package com.soprasteria.ws.rest.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.soprasteria.ws.rest.createObject.MovieObject;
import com.soprasteria.ws.rest.dao.MovieDAO;
import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.entity.MovieEntity;
import com.soprasteria.ws.rest.service.impl.MovieServiceImpl;
import com.soprasteria.ws.rest.utils.builders.MovieBuilder;
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

		movieService.getMovieId(id);

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

	movieService.getMovieList();

	}

	@Test
	public void whenExistList_thenListShouldBeShow() throws ListIsEmptyException {

		List<MovieEntity> movieList = new ArrayList<>();
		Long id = 1L;
		MovieEntity movie1 = MovieObject.createMovieEntity(id);
		movieList.add(movie1);
		when(movieDAO.findAll()).thenReturn(movieList);

		List<MovieResponse> listActual = movieService.getMovieList();

		assertEquals(1, listActual.size());
	}

	@Test(expected = MovieExistsException.class)
	public void whenMovieExists_thenMovieShouldNotBeSaved() throws Throwable {

		MovieRequest movieToSave = MovieObject.createMovieRequest();
	
		Long id = 1L;
		MovieEntity dto = MovieObject.createMovieEntity(id);
		when(movieDAO.findMovieDTOByTitleIgnoreCase("Titulo test")).thenReturn(Optional.of(dto));


		 movieService.save(movieToSave);
	}

	@Test
	public void whenMovieInOk_thenMovieShouldBeSaved() throws MovieExistsException {

		Long id = 1L;
		MovieRequest movieToSave = MovieObject.createMovieRequest();
		when(movieDAO.findMovieDTOByTitleIgnoreCase(movieToSave.getTitle())).thenReturn(Optional.empty());

		MovieEntity movieEntity = MovieObject.createMovieEntity(id);
		when(builder.toMovieEntity(movieToSave)).thenReturn(movieEntity);

		MovieEntity movieEntitySaved = MovieObject.createMovieEntity(1L);
		when(movieDAO.saveAndFlush(movieEntity)).thenReturn(movieEntitySaved);

		MovieResponseFull movieResponse = MovieObject.createMovieResponseFull(id);
		when(builder.toMovieResponseFull(movieEntitySaved)).thenReturn(movieResponse);
		
		MovieResponseFull actual = movieService.save(movieToSave);

		assertEquals(id, actual.getId());

	}

	@Test
	public void whenExistMovieTitle_thenMovieShouldBeFound() {

		Long id = 1L;
		
		String titleIn = "Titulo test";
		MovieEntity dto = MovieObject.createMovieEntity(1L);
		when(movieDAO.findMovieDTOByTitleIgnoreCase(titleIn)).thenReturn(Optional.of(dto));
		
		MovieResponseFull movieResponseFull = MovieObject.createMovieResponseFull(id);
		when(builder.toMovieResponseFull(dto)).thenReturn(movieResponseFull);


		MovieResponseFull actual = movieService.getMovieByTitle(titleIn);

		Assert.assertEquals(id, actual.getId());
	}

	@Test(expected = OrderNotFoundException.class)
	public void whenNotExitMovieByTitle_thenOrderNotFoundException() {

		String titleIn = "Titulo de prueba";
		Mockito.when(movieDAO.findMovieDTOByTitleIgnoreCase(titleIn)).thenReturn(Optional.empty());

		movieService.getMovieByTitle(titleIn);

	}
	
	@Test(expected = OrderNotFoundException.class)
	public void whenNotExitMovieByGenre_thenOrderNotFoundException() {

		String genre = "DRAMA";
		Mockito.when(movieDAO.findMovieDTOByGenreIgnoreCase(genre)).thenReturn(Optional.empty());

		movieService.getMovieByGenre(genre);

	}
	
	@Test
	public void whenExistMovieByGenre_thenMovieShouldBeFound() {

		Long id = 1L;
		
		String genre = "Drama";
		MovieEntity dto = MovieObject.createMovieEntity(1L);
		List<MovieEntity> movieEntityList = new ArrayList<>();
		movieEntityList.add(dto);
		
		when(movieDAO.findMovieDTOByGenreIgnoreCase(genre)).thenReturn(Optional.of(movieEntityList));
		
		MovieResponseFull movieResponseFull = MovieObject.createMovieResponseFull(id);
		when(builder.toMovieResponseFull(dto)).thenReturn(movieResponseFull);


		List<MovieResponseFull> actual = movieService.getMovieByGenre(genre);

		Assert.assertEquals(1, actual.size());
	}

	
	@Test(expected = OrderNotFoundException.class)
	public void whenNotExistsMovie_thenMovieShouldNotBeUpdate() throws Throwable {

		MovieRequest movieToSave = MovieObject.createMovieRequest();
	
		Long id = 1L;
		MovieEntity dto = MovieObject.createMovieEntity(id);
		when(movieDAO.findMovieDTOById(id)).thenReturn(Optional.empty());


		 movieService.update(movieToSave, id);
	}
	
////	@Test
//	@Ignore
//	public void whenExistsMovie_thenMovieShouldBeUpdate() throws Throwable {
//
//		MovieRequest movieToSave = MovieObject.createMovieRequest();
//	
//		Long id = 1L;
//		MovieEntity dto = MovieObject.createMovieEntity(id);
//		when(movieDAO.findMovieDTOById(id)).thenReturn(Optional.of(dto));
//		
//		MovieEntity updated =MovieObject.createMovieEntity(id);
//		updated.setTitle("Titulo test");
//		updated.setGenre("Genero");
//		updated.setYear(2000);
//		
////		when(movieDAO.saveAndFlush(updated)).thenReturn(updated);
//		
//		MovieResponseFull response = MovieObject.createMovieResponseFull(id);
//		
//		when(builder.toMovieResponseFull(updated)).thenReturn(response);
//		
//MovieResponseFull actual = movieService.update(movieToSave, id);
//		
//		assertEquals(id, actual.getId());
//
//		 movieService.update(movieToSave, id);
//	}
//	
	@Test(expected = OrderNotFoundException.class)
	public void whenNotExistsMovie_thenMovieShouldNotBeDelete() throws Throwable {

	
		Long id = 1L;
	
		when(movieDAO.findMovieDTOById(id)).thenReturn(Optional.empty());


		 movieService.delete(id);
	}
	
	@Test(expected = OrderNotFoundException.class)
	public void whenExistsMovie_thenMovieShouldBeDelete() throws Throwable {

		MovieRequest movieToUpdate = MovieObject.createMovieRequest();
	
		Long id = 1L;
		MovieEntity dto = MovieObject.createMovieEntity(id);
		when(movieDAO.findMovieDTOById(id)).thenReturn(Optional.of(dto));


		StringBuffer expected = new StringBuffer();
		expected.append("");
		
		String actual = movieService.delete(id);
		 
		 assertEquals(expected, actual);
	}
}
