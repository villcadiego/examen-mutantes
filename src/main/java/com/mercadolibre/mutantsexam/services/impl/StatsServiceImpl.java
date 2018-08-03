package com.mercadolibre.mutantsexam.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutantsexam.dao.DataBaseDAO;
import com.mercadolibre.mutantsexam.dto.Stats;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.exceptions.StatsException;
import com.mercadolibre.mutantsexam.services.StatsService;


@Service("statsService")
public class StatsServiceImpl implements StatsService {
	private static final Logger logger = LogManager.getLogger(StatsServiceImpl.class);
	
	@Autowired
	private DataBaseDAO dataBaseDAO;
	
	@Override
	public Stats getStats() throws StatsException{
		Stats stats;

		try {
			long count_mutant_dna = dataBaseDAO.getMutantsCount();
			long count_human_dna  = dataBaseDAO.getHumansCount();
			
			stats = new Stats(count_mutant_dna, count_human_dna);
		} catch (MutantDBException ex) {
			logger.error("Error connecting to the database", ex);
			throw new StatsException("Error getting statistics");
		}
		return stats;
	}
	
	@Override
	public boolean reset() throws StatsException, MutantDBException {
			dataBaseDAO.reset();
		return false;
	}
}