package com.mercadolibre.mutantsexam.entities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.mutantsexam.constants.ConstantsMutant;
import com.mercadolibre.mutantsexam.interfaces.SearchMutant;
import com.mercadolibre.mutantsexam.utils.SearchUtils;

/**
 * @author dvillca
 * Concrete Strategy
 */
public class SearchDiagonalDesc implements SearchMutant {	
	
	private static final Logger logger = LogManager.getLogger(SearchDiagonalDesc.class);

	public SearchDiagonalDesc() {
	}

	/**
	 * implementation of mutant search by the descending diagonal method
	 * 
	 */
	@Override
	public boolean searchMutants(char[][] matrix) {
		//TODO this process is limited to a square matrix. improve the implementation
		
		int count_patterns = 0;
		
		for (int i = (matrix.length - ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA) ; i >= 0 ; i--) { //limitation of diagonals
			String dna_elements = "";
		    for (int j = 0; j < (matrix.length - i); j ++) {
		    	dna_elements += matrix[i + j][j];
		    }
		    count_patterns += SearchUtils.getPatternsOfConsecutiveDNAElements(dna_elements);
			if(count_patterns >= ConstantsMutant.QUANTITY_MINIMAL_SEQUENCES_MUTANT)
				return true;		
		}

		for (int i = 0; i < (matrix.length - ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA); i++) { //limitation of diagonals
			String dna_elements = "" ;
		    for (int j=0 ; j < matrix.length -i -1; j++) { 
		    	dna_elements +=matrix[j][j+i+1];
		    }
		    count_patterns += SearchUtils.getPatternsOfConsecutiveDNAElements(dna_elements);
			if(count_patterns >= ConstantsMutant.QUANTITY_MINIMAL_SEQUENCES_MUTANT)
				return true;		
		}
		logger.debug("SearchDiagonalDesc : no patterns were found");
		return false;
	}
}