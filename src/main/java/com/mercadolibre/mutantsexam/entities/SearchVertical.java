package com.mercadolibre.mutantsexam.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.mutantsexam.constants.ConstantsMutant;
import com.mercadolibre.mutantsexam.interfaces.SearchMutant;
import com.mercadolibre.mutantsexam.utils.SearchUtils;

public class SearchVertical implements SearchMutant {	
	
	private static final Logger logger = LogManager.getLogger(SearchVertical.class);

	public SearchVertical() {
	}
	
	/**
	 * Implementation of mutant search by the vertical method
	 */
	@Override
	public boolean searchMutants(char[][] matrix) {
		
		int count_patterns = 0;
		for (int i = 0; i < matrix.length; i++) {
			String dna_elements = "";
			for (int j = 0; j < matrix.length; j++) {
				dna_elements += matrix[j][i];
			}
			count_patterns += SearchUtils.getPatternsOfConsecutiveDNAElements(dna_elements);
			if(count_patterns >= ConstantsMutant.QUANTITY_MINIMAL_SEQUENCES_MUTANT)
				return true;
		}
		logger.debug("SearchVertical : no patterns were found");
		return false;
	}

}