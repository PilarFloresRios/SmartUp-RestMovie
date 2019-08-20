package com.soprasteria.ws.rest.utils.validators;

import java.util.Calendar;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.soprasteria.ws.rest.utils.annotations.Year;

@Component
public class YearValidator implements ConstraintValidator<Year, Integer>{

	 @Override
	    public void initialize(Year year) {
		 // Default initialize
	    }


	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		
		Calendar cal= Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
	
		if (value>= 1800 && value<=(year+5)) {
		
		
			return true;
		} 
		
		return false;
	}
	
	/*
	 * CONSULTA: Se mejoria utilizando compareTo?
	 */

}
