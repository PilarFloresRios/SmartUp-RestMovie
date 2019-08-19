package com.soprasteria.ws.rest.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.soprasteria.ws.rest.utils.validators.GenreValidator;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenreValidator.class)
public @interface Genre {
	
	String message() default "{movie.genre}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	

}
