package com.soprasteria.ws.rest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.ws.rest.entity.MovieEntity;

public interface MovieDAO extends JpaRepository<MovieEntity, Long> {

	Optional<MovieEntity> findMovieDTOById(Long id); 
	
	Optional<MovieEntity> findMovieDTOByTitle(String title);
	
	Optional<MovieEntity> findMovieDTOByGenre(String genre);
	
	Optional<List<MovieEntity>> findMovieDTOByYear(int year);
	
////	Optional<MovieEntity> updateOrSave(MovieEntity movie);
//	
//	Optional<MovieEntity> delete(Long id);

}
