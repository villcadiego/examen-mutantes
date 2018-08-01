package com.mercadolibre.interfaces;

/**
 * @author dvillca
 * The goal is to be able to have different mutant search strategies
 * 
 * Strategy pattern
 */
public interface SearchMutant {
	
	public boolean searchMutants(char[][] matriz);
}