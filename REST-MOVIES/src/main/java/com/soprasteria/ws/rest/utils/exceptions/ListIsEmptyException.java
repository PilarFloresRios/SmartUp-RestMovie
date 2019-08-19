package com.soprasteria.ws.rest.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The list is empty") // 304
public class ListIsEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

}
