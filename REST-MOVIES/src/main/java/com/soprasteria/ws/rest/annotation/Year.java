package com.soprasteria.ws.rest.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.soprasteria.ws.rest.validator.YearValidator;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidator.class)
public @interface Year {

	String message() default "{movie.year}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
