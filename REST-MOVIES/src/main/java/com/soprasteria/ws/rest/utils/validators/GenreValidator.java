package com.soprasteria.ws.rest.utils.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.soprasteria.ws.rest.utils.Constans;
import com.soprasteria.ws.rest.utils.annotations.Genre;

@Component
public class GenreValidator implements ConstraintValidator<Genre, String>{

	 @Override
	    public void initialize(Genre genre) {
		 // Default initialize
	    }

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		List<String> genres = new ArrayList<>();
		
		genres.add(Constans.getDrama());
		genres.add(Constans.getRomance());
		genres.add(Constans.getScienceFiction());
		genres.add(Constans.getTerror());
		
		if (value != null && genres.contains(value.toUpperCase())) {
			return true;
		} else {
		
		return false;
		}
	}

}
