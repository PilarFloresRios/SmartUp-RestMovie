package com.soprasteria.ws.rest.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "The movie already exists") // 302
public class MovieExistsException extends Exception {

	private static final long serialVersionUID = 1L;

}
