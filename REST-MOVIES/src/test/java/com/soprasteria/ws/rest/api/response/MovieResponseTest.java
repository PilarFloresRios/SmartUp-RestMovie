package com.soprasteria.ws.rest.api.response;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soprasteria.ws.rest.response.movie.MovieResponse;

@RunWith(JUnit4.class)
public class MovieResponseTest {

	private MovieResponse movieResponse;

	@Before
	public void setUp() throws Exception {

		movieResponse = new MovieResponse();

		movieResponse.setTitle("Titulo");
		movieResponse.setYear(2001);

	}

	@Test
	public void MovieResponseSettersTest() {

		assertNotNull(movieResponse);
	}

	@Test
	public void MovieResponseGettersTest() {

		assertNotNull("Fail setter movieResponse.getTitle", movieResponse.getTitle());
		assertNotNull("Fail setter movieResponse.getYear", movieResponse.getYear());

	}

	@Test
	public void MovieResponseToStringTest() {

		String expected = "MovieResponse [id=null, title=Titulo, year=2001]";
		String actual = movieResponse.toString();

		assertEquals(expected, actual);

	}
}
