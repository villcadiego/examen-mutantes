package com.mercadolibre.exceptions;

/**
 * @author dvillca
 *
 */
public class StatsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public StatsException() {}

	public StatsException(String message) {
		super(message);
	}
}
