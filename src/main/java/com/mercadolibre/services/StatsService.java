package com.mercadolibre.services;

import com.mercadolibre.dto.Stats;
import com.mercadolibre.exceptions.StatsException;

/**
 * @author dvillca
 */

public interface StatsService {
	public Stats getStats() throws StatsException;
}
