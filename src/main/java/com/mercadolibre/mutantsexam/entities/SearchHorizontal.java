package com.mercadolibre.mutantsexam.entities;
import com.mercadolibre.mutantsexam.interfaces.SearchMutant;
import com.mercadolibre.mutantsexam.utils.SearchUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.mutantsexam.constants.ConstantsMutant;


/**
 * @author dvillca
 * Concrete Strategy
 */
public class SearchHorizontal implements SearchMutant{
	
	private static final Logger logger = LogManager.getLogger(SearchDiagonalDesc.class);
	
	public SearchHorizontal() {
		
	}
	
	/**
	 * Implementation of mutant search by horizontal method
	 */
	@Override
	public boolean searchMutants(char[][] matrix) {
		int count_patterns = 0;
		for (int i = 0; i < matrix.length; i++) {
			String dna_elements = "";
			for (int j = 0; j < matrix.length; j++) {
				dna_elements += matrix[i][j];
			}
			count_patterns += SearchUtils.getPatternsOfConsecutiveDNAElements(dna_elements);
			if(count_patterns >= ConstantsMutant.QUANTITY_MINIMAL_SEQUENCES_MUTANT)
				return true;
		}
		logger.debug("SearchHorizontal : no patterns were found");
		return false;
	}
}