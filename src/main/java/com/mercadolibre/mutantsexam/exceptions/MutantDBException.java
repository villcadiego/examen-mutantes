package com.mercadolibre.mutantsexam.exceptions;

/**
 * @author dvillca
 *
 */
public class MutantDBException extends Exception{

	private static final long serialVersionUID = 1L;

	public MutantDBException() {}

	public MutantDBException(String message) {
		super(message);
	}
}
