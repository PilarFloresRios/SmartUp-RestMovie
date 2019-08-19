package com.soprasteria.ws.rest.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect year format.") // 400
public class YearFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
