package com.soprasteria.ws.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.soprasteria.ws.rest.exception.ListIsEmptyException;
import com.soprasteria.ws.rest.exception.MovieExistsException;
import com.soprasteria.ws.rest.exception.OrderNotFoundException;

//import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.ws.rest.builder.MovieBuilder;
import com.soprasteria.ws.rest.dao.MovieDAO;
import com.soprasteria.ws.rest.entity.MovieEntity;
import com.soprasteria.ws.rest.request.movie.MovieRequest;
import com.soprasteria.ws.rest.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.service.MovieService;
import com.soprasteria.ws.rest.response.movie.MovieResponse;

/**
 * <h1>Class MovieService</h1> Include HTTP methods.
 * 
 * @author mpflores
 * @since July 2019
 * @version 0.0.1
 */
@Service
public class MovieServiceImpl implements MovieService {

	MovieDAO dao;

	MovieBuilder builder;

	@Autowired
	public MovieServiceImpl(MovieDAO dao, MovieBuilder builder) {
		super();
		this.dao = dao;
		this.builder = builder;
	}

	// @Query("select m from MOVIE p where m.idMovie = 1") // MIRAR A VER COMO SE
	// HACE
//	https://www.arquitecturajava.com/usando-java-8-optional/
//	https://www.baeldung.com/spring-data-jpa-null-parameters
	/**
	 * Method to search for a movie by id.
	 * 
	 * @param ID of the movie to search.
	 * @return Movie with indicated ID.
	 */
	@Override
	public MovieResponseFull getMovieId(Long id) {

		if (dao.findById(id).isPresent()) {

			MovieEntity movieEntity = dao.findById(id).get();

			MovieResponseFull movieResponse = builder.toMovieResponseFull(movieEntity);

			return movieResponse;

		} else {

			throw new OrderNotFoundException();

		}

	}

	@Override
	public MovieResponseFull getMovieByTitle(String titleIn) {

		String title = titleIn.replace("_", " ");

		if (dao.findMovieDTOByTitle(title).isPresent()) {

			MovieEntity movieEntity = dao.findMovieDTOByTitle(title).get();
//			MovieResponseFull movieResponse = movieEntity.toMovieResponseFull();
			MovieResponseFull movieResponse = builder.toMovieResponseFull(movieEntity);

			return movieResponse;

		} else {

			throw new OrderNotFoundException();

		}

	}

	/**
	 * Method to saved a movie.
	 * 
	 * @param movie to save.
	 * @return movie saved.
	 */
	@Override
	public MovieResponseFull save(MovieRequest movie) throws MovieExistsException {

		if (dao.findMovieDTOByTitle(movie.getTitle()).isPresent()) {
			throw new MovieExistsException();
		} else {

//			MovieEntity movieEntity = movie.toMovieEntity();
			MovieEntity movieEntity = builder.toMovieEntity(movie);
			MovieEntity movieSave = dao.saveAndFlush(movieEntity);
			MovieResponseFull movieResponse = builder.toMovieResponseFull(movieSave);
			return movieResponse;
		}
	}

	/**
	 * Method to show all movies, title and genre is shown.
	 * 
	 * @return List of movies.
	 * @throws ListIsEmptyException
	 */
	@Override
	public List<MovieResponse> getMovieList() throws ListIsEmptyException {

		List<MovieEntity> movieEntityList = dao.findAll();
		List<MovieResponse> movieResponseList = new ArrayList<MovieResponse>();

		if (movieEntityList.isEmpty()) {

//			throw new ListIsEmptyException();
			throw new ListIsEmptyException();

		} else {
			for (int i = 0; i < movieEntityList.size(); i++) {

				MovieEntity movieEntity = movieEntityList.get(i);
				MovieResponse movieResponse = builder.toMovieResponse(movieEntity);
				movieResponseList.add(movieResponse);

			}

			return movieResponseList;
		}
	}

	@Override
	public MovieResponseFull getMovieByGenre(String genre) {
		if (dao.findMovieDTOByGenre(genre).isPresent()) {

			MovieEntity movieEntity = dao.findMovieDTOByGenre(genre).get();
			MovieResponseFull movieResponse = builder.toMovieResponseFull(movieEntity);

			return movieResponse;

		} else {

			throw new OrderNotFoundException();

		}

	}

	/**
	 * Method to saved a movie.
	 * 
	 * @param movie to save.
	 * @return movie saved.
	 */
	@Override
	public MovieResponseFull update(MovieRequest movie, Long id) {

		if (dao.findMovieDTOById(id).isPresent()) {

			MovieEntity movieToUpdate = dao.findMovieDTOById(id).get();
			if (!movie.getTitle().isEmpty()) {
				movieToUpdate.setTitle(movie.getTitle());
			}

			if (!movie.getGenre().isEmpty()) {
				movieToUpdate.setGenre(movie.getGenre());
			}
			if (!(movie.getYear() == 0)) {
				movieToUpdate.setYear(movie.getYear());
			}

			/* falta actualizar y añadir actores */

			dao.saveAndFlush(movieToUpdate);
			MovieResponseFull movieResponse = builder.toMovieResponseFull(movieToUpdate);

			return movieResponse;
		} else {

			throw new OrderNotFoundException();
		}
	}

	@Override
	public StringBuffer delete(long id) {

		if (dao.findById(id).isPresent()) {

			MovieEntity movieEntity = dao.findById(id).get();

			String movieDeleted = movieEntity.toString();
			StringBuffer message = new StringBuffer();

			message.append("[DELETED: ").append(movieDeleted);
			dao.delete(movieEntity);

			return message;

		} else {

			throw new OrderNotFoundException();

		}
	}

	/*
	 * HACER: Internar hacer un metodo buscando por query y no a traves de borar
	 * registros en la base de datos CONSULTA: Crear clase nueva MovieService y
	 * renombrar esta como MovieServiceImpl, hasta que punto
	 */
}
