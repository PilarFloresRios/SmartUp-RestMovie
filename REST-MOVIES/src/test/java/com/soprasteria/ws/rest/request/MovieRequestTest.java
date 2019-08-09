package com.soprasteria.ws.rest.request;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.soprasteria.ws.rest.request.movie.MovieRequest;

public class MovieRequestTest {

	private MovieRequest movieRequest;

	@Before
	public void setUp() throws Exception {

		movieRequest = new MovieRequest();

		String actors[] = {"Actor2"};

//		movieRequest.setIdMovie(1L);
		movieRequest.setTitle("Titulo");
		movieRequest.setGenre("Genero");
		movieRequest.setYear(2000);
		movieRequest.setActors(actors);

	}

	@Test
	public void MovieRequestSettersTest() {

		assertNotNull(movieRequest);
	}

	@Test
	public void MovieRequestGettersTest() {

//		assertNotNull("Fail setter movieRequest.getIdMovie",movieRequest.getIdMovie());

		assertNotNull("Fail setter movieRequest.getTitle",movieRequest.getTitle());
		assertNotNull("Fail setter movieRequest.getGenre",movieRequest.getGenre());
		assertNotNull("Fail setter movieRequest.getYear",movieRequest.getYear());
		assertNotNull("Fail setter movieRequest.getActors",movieRequest.getActors());

	}

	@Test
	public void MovieRequestToStringTest() {

		String expected = "MovieRequest [title=Titulo, genre=Genero, year=2000, actors=[Actor]]";
		String actual = movieRequest.toString();

		assertEquals(expected, actual);

	}

}
