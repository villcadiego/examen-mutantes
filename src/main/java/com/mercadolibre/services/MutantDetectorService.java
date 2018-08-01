package com.mercadolibre.services;

import com.mercadolibre.exceptions.DNAException;
import com.mercadolibre.exceptions.MutantDBException;
import com.mercadolibre.exceptions.DNAElementException;

/**
 * @author dvillca
 */

public interface MutantDetectorService {
	public boolean isMutant(String[] dna) throws DNAException, DNAElementException, MutantDBException;
}
