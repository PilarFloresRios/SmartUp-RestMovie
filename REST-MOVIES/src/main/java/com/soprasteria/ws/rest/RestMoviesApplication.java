package com.soprasteria.ws.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.soprasteria.ws.rest.utils.builders.MovieBuilder;

@SpringBootApplication
public class RestMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestMoviesApplication.class, args);
	}

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }
    
    
    @Bean
    public MovieBuilder movieBuilder() {

        return new MovieBuilder();
    }
    /*
     * CONSULTA: Lo correcto seria crear un validador bean dentro del parquete bean?
     */
}
