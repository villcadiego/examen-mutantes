package com.mercadolibre.mutantsexam.services;

import com.mercadolibre.mutantsexam.exceptions.DNAException;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.exceptions.DNAElementException;

/**
 * @author dvillca
 */

public interface MutantDetectorService {
	public boolean isMutant(String[] dna) throws DNAException, DNAElementException, MutantDBException;
}
