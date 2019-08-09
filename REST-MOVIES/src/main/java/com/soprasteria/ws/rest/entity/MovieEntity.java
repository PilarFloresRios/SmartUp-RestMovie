package com.soprasteria.ws.rest.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.soprasteria.ws.rest.builder.concrete.MovieResponseBuilder;
//import com.soprasteria.ws.rest.builder.concrete.MovieResponseFullBuilder;
//import com.soprasteria.ws.rest.response.movie.MovieResponse;
//import com.soprasteria.ws.rest.response.movie.MovieResponseFull;

@Entity
@Table(name = "MOVIES")
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = 4894729030347835498L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String title;

	@Column
	private String genre;

	@Column
	private int year;

	
	@Column	
	private String actors[] = null;
	
	
	
//	public MovieDTO(Long id, String title, String genre, int year, String[] actors) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.genre = genre;
//		this.year = year;
//		this.actors = actors;
//	}

	public MovieEntity() {

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieDTO [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", genre=");
		builder.append(genre);
		builder.append(", year=");
		builder.append(year);
		builder.append(", actors=");
		builder.append(Arrays.toString(actors));
		builder.append("]");
		return builder.toString();
	}

//	public MovieResponse toMovieResponse() {
//		MovieResponseBuilder builder = new MovieResponseBuilder();
//
//		builder.setId(id);
//		builder.setTitle(title);
//		builder.setYear(year);
//
//		return builder.getResult();
//	}
//	
//	public MovieResponseFull toMovieResponseFull() {
//		MovieResponseFullBuilder builder = new MovieResponseFullBuilder();
//
//		builder.setId(id);
//		builder.setTitle(title);
//		builder.setYear(year);
//		builder.setGenre(genre);
//		builder.setActors(actors);
//
//		return builder.getResult();
//	}
}
