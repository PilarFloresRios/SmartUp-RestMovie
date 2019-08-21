package com.soprasteria.ws.rest.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


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
	private String[] actors = null;

	public MovieEntity() {
		// Constructor
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
		this.title = title.toUpperCase();
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre.toUpperCase();
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

}
