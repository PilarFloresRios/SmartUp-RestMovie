package com.soprasteria.ws.rest.utils;

import com.soprasteria.ws.rest.utils.exceptions.YearFormatException;

public class Comuns {

	private Comuns() {
		super();
	}

	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			throw new YearFormatException();
		}
	}
}
