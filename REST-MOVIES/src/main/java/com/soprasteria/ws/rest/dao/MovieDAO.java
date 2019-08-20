package com.soprasteria.ws.rest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soprasteria.ws.rest.entity.MovieEntity;

public interface MovieDAO extends JpaRepository<MovieEntity, Long> {

	Optional<MovieEntity> findMovieDTOById(Long id); 
	
	Optional<MovieEntity> findMovieDTOByTitleIgnoreCase(String title);
	
	Optional<List<MovieEntity>> findMovieDTOByTitleContainingIgnoreCase(String title);
	
	Optional<List<MovieEntity>> findMovieDTOByGenreIgnoreCase(String genre);
	
	Optional<List<MovieEntity>> findMovieDTOByYear(int year);
	
	@Query(value = "SELECT * FROM MOVIES m WHERE m.title LIKE %:title% AND m.genre = :genre AND m.year = :year", 
			  nativeQuery = true)
	Optional<List<MovieEntity>> findMovieTitleAndGenreAndYear(String title, String genre, String year);
	
	@Query(value = "SELECT * FROM MOVIES m WHERE m.title LIKE %:title% AND m.genre = :genre  ", 
			  nativeQuery = true)
	Optional<List<MovieEntity>> findMovieTitleAndGenre(String title, String genre);
	
	@Query(value = "SELECT * FROM MOVIES m WHERE m.title LIKE %:title% and m.year = :year", 
			  nativeQuery = true)
	Optional<List<MovieEntity>> findMovieTitleAndYear(String title, String year);
	
	@Query(value = "SELECT * FROM MOVIES m WHERE m.genre = :genre and m.year = :year", 
			  nativeQuery = true)
	Optional<List<MovieEntity>> findMovieGenreAndYear(String genre, String year);
	

}
