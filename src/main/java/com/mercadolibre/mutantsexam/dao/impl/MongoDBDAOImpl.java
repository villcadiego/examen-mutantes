package com.mercadolibre.mutantsexam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutantsexam.dao.DataBaseDAO;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.model.DnaHuman;
import com.mercadolibre.mutantsexam.model.DnaMutant;
import com.mercadolibre.mutantsexam.repositories.DnaRepositoryHuman;
import com.mercadolibre.mutantsexam.repositories.DnaRepositoryMutant;

@Service
public class MongoDBDAOImpl implements DataBaseDAO{
	
	
	@Autowired
    private DnaRepositoryHuman dnaRepositoryHuman;
	
	@Autowired
    private DnaRepositoryMutant dnaRepositoryMutant;
	
	@Override
	public void insertDNA(String[] dna, boolean isMutant) throws MutantDBException {
		
		if(isMutant) {
			DnaMutant object = new DnaMutant();
			object.setSequence(dna);
			dnaRepositoryMutant.save(object);
		}else {
			DnaHuman object = new DnaHuman();
			object.setSequence(dna);
			dnaRepositoryHuman.save(object);
		}
	}

	@Override
	public long getHumansCount() throws MutantDBException {
		List<DnaHuman> list = new ArrayList<>();
		dnaRepositoryHuman.findAll().forEach(list::add);
        return list.size();
	}

	@Override
	public long getMutantsCount() throws MutantDBException {
		List<DnaMutant> list = new ArrayList<>();
		dnaRepositoryMutant.findAll().forEach(list::add);
		return list.size();
	}
	
	@Override
	public boolean reset() throws MutantDBException {
		dnaRepositoryMutant.deleteAll();
		dnaRepositoryHuman.deleteAll();
		return true;
	}
}