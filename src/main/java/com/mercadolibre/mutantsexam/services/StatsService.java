package com.mercadolibre.mutantsexam.services;

import com.mercadolibre.mutantsexam.dto.Stats;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.exceptions.StatsException;

/**
 * @author dvillca
 */

public interface StatsService {
	public Stats getStats() throws StatsException;
	public boolean reset() throws StatsException, MutantDBException;
}
