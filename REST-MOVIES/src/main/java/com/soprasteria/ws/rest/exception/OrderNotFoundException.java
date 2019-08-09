package com.soprasteria.ws.rest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order") // 404
public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
