package com.soprasteria.ws.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.ws.rest.dao.MovieDAO;
import com.soprasteria.ws.rest.dto.request.movie.MovieRequest;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponse;
import com.soprasteria.ws.rest.dto.response.movie.MovieResponseFull;
import com.soprasteria.ws.rest.entity.MovieEntity;
import com.soprasteria.ws.rest.service.MovieService;
import com.soprasteria.ws.rest.utils.Comuns;
import com.soprasteria.ws.rest.utils.Constans;
import com.soprasteria.ws.rest.utils.builders.MovieBuilder;
import com.soprasteria.ws.rest.utils.exceptions.ListIsEmptyException;
import com.soprasteria.ws.rest.utils.exceptions.MovieExistsException;
import com.soprasteria.ws.rest.utils.exceptions.OrderNotFoundException;

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

			return builder.toMovieResponseFull(movieEntity);

		} else {

			throw new OrderNotFoundException();

		}

	}

	@Override
	public MovieResponseFull getMovieByTitle(String titleIn) {

		String title = titleIn.replaceAll(Constans.getUnderscore(), Constans.getBlankSpace());

		if (dao.findMovieDTOByTitleIgnoreCase(title).isPresent()) {

			MovieEntity movieEntity = dao.findMovieDTOByTitleIgnoreCase(title).get();

			return builder.toMovieResponseFull(movieEntity);

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

		if (dao.findMovieDTOByTitleIgnoreCase(movie.getTitle()).isPresent()) {
			throw new MovieExistsException();
		} else {

			MovieEntity movieEntity = builder.toMovieEntity(movie);
			MovieEntity movieSave = dao.saveAndFlush(movieEntity);
			
			return builder.toMovieResponseFull(movieSave);
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
		List<MovieResponse> movieResponseList = new ArrayList<>();

		if (movieEntityList.isEmpty()) {

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
	public List<MovieResponseFull> getMovieByGenre(String genre) {
		if (dao.findMovieDTOByGenreIgnoreCase(genre).isPresent()) {

			List<MovieEntity> movieEntityList = dao.findMovieDTOByGenreIgnoreCase(genre).get();
			List<MovieResponseFull> movieResponseList = new ArrayList<>();

			for (int i = 0; i < movieEntityList.size(); i++) {

				MovieEntity movieEntity = movieEntityList.get(i);
				MovieResponseFull movieResponse = builder.toMovieResponseFull(movieEntity);
				movieResponseList.add(movieResponse);

			}

			return movieResponseList;

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
			if ((movie.getYear() != 0)) {
				movieToUpdate.setYear(movie.getYear());
			}

			/* falta actualizar y añadir actores */

			dao.saveAndFlush(movieToUpdate);

			return builder.toMovieResponseFull(movieToUpdate);
			
		} else {

			throw new OrderNotFoundException();
		}
	}

	@Override
	public String delete(long id) {

		if (dao.findById(id).isPresent()) {

			MovieEntity movieEntity = dao.findById(id).get();

			String movieDeleted = movieEntity.toString();
			StringBuilder  message = new StringBuilder ();

			dao.delete(movieEntity);
			message.append("[DELETED: ").append(movieDeleted);

			return message.toString();

		} else {

			throw new OrderNotFoundException();

		}
	}

	@Override
	public List<MovieResponseFull> seachMovie(String title, String genre, String year) throws ListIsEmptyException {

		

		Optional<List<MovieEntity>> movieEntityList = null;

		if (title != null && title.length() != 0) {
			String titleToSeach = title.replaceAll(Constans.getUnderscore(), Constans.getBlankSpace());
			movieEntityList = findMovieWithTitle(titleToSeach.toUpperCase(), genre, year);

		} else {			
			movieEntityList = findMovieWithOutTitle(genre, year);
		}

		List<MovieResponseFull> movieResponseList = new ArrayList<>();

		if (movieEntityList.isPresent()) {

			List<MovieEntity> list = movieEntityList.get();

			for (int i = 0; i < list.size(); i++) {

				MovieEntity movieEntity = list.get(i);
				MovieResponseFull movieResponse = builder.toMovieResponseFull(movieEntity);
				movieResponseList.add(movieResponse);

			}
			return movieResponseList;

		} else {
			throw new ListIsEmptyException();

		}

	}
	
	
	public Optional<List<MovieEntity>> findMovieWithTitle(String title, String genre, String year) {
	
		Optional<List<MovieEntity>> movieEntityList = null;

		if (genre != null) {
			if (year != null) {
				if (Comuns.isNumeric(year)) {
					movieEntityList = dao.findMovieTitleAndGenreAndYear(title, genre.toUpperCase(), year);
				}
			} else {
				movieEntityList = dao.findMovieTitleAndGenre(title, genre.toUpperCase());
			}
		} else {
			if (year != null) {
				if (Comuns.isNumeric(year)) {
					movieEntityList = dao.findMovieTitleAndYear(title, year);
				}

			} else {

				movieEntityList = dao.findMovieDTOByTitleContainingIgnoreCase(title);
			}
		}

		return movieEntityList;
	
	}
	
	
	public Optional<List<MovieEntity>> findMovieWithOutTitle(String genre, String year) {
		
		Optional<List<MovieEntity>> movieEntityList = null;

		if (genre != null) {
			if (year != null) {
				if (Comuns.isNumeric(year)) {
					movieEntityList = dao.findMovieGenreAndYear(genre.toUpperCase(), year);
				}
			} else {
				movieEntityList = dao.findMovieDTOByGenreIgnoreCase(genre.toUpperCase());
			}

		} else if (year != null && Comuns.isNumeric(year)) {
			
				movieEntityList = dao.findMovieDTOByYear(Integer.parseInt(year));
			
		}
		
		return movieEntityList;
	
	}
}
