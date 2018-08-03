package com.mercadolibre.mutantsexam.dao;

import com.mercadolibre.mutantsexam.exceptions.MutantDBException;

public interface DataBaseDAO {

	public void insertDNA(String[] dna, boolean isMutant) throws MutantDBException;
	public long getHumansCount() throws MutantDBException;
	public long getMutantsCount() throws MutantDBException;
	public boolean reset() throws MutantDBException;
}
