package com.mercadolibre.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.dao.DataBaseDAO;
import com.mercadolibre.dto.Stats;
import com.mercadolibre.exceptions.MutantDBException;
import com.mercadolibre.exceptions.StatsException;
import com.mercadolibre.services.StatsService;


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
			double ratio = calculateRatio(count_mutant_dna, count_human_dna);
			stats = new Stats(count_mutant_dna, count_human_dna, ratio);
		} catch (MutantDBException ex) {
			logger.error("Error connecting to the database", ex);
			throw new StatsException("Error getting statistics");
		}
		return stats;
	}
	
	private double calculateRatio(long count_mutant_dna, long count_human_dna) {
		//TODO  solve calculation operation
		return 0.4d;
	}
}