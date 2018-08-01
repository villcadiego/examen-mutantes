package com.mercadolibre.dao;

import com.mercadolibre.exceptions.MutantDBException;

public interface DataBaseDAO {

	public void insertDNA(String[] dna, boolean isMutant) throws MutantDBException;
	
	public int getHumansCount() throws MutantDBException;
	public int getMutantsCount() throws MutantDBException;

}
