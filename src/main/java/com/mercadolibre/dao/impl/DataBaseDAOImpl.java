package com.mercadolibre.dao.impl;

import org.springframework.stereotype.Service;

import com.mercadolibre.dao.DataBaseDAO;
import com.mercadolibre.exceptions.MutantDBException;

@Service
public class DataBaseDAOImpl implements DataBaseDAO{

	@Override
	public void insertDNA(String[] dna, boolean isMutant) throws MutantDBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHumansCount() throws MutantDBException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMutantsCount() throws MutantDBException {
		// TODO Auto-generated method stub
		return 0;
	}

}
