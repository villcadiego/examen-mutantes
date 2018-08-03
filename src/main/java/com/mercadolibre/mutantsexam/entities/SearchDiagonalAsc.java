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
public class SearchDiagonalAsc implements SearchMutant {
	
	private static final Logger logger = LogManager.getLogger(SearchDiagonalAsc.class);

	public SearchDiagonalAsc() {
		
	}
	
	/**
	 * Implementation of search for mutants by the ascending diagonal method
	 */
	
	@Override
	public boolean searchMutants(char[][] matrix) {
		int count_patterns = 0;
		
		//the matrix is traversed in nested iterations
		
		for (int i = (ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA - 1);i < matrix.length ; i++) { // //limitation of diagonals
			String dna_elements = "";
		    for (int j = 0; j <= i; j ++) {
		    	dna_elements += matrix[i-j][j];
		    }
		    count_patterns += SearchUtils.getPatternsOfConsecutiveDNAElements(dna_elements);
			if(count_patterns >= ConstantsMutant.QUANTITY_MINIMAL_SEQUENCES_MUTANT)
				return true;		
		}

		for (int i=0;i < (matrix.length - ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA); i++) { // //limitation of diagonals
			String dna_elements = "";
		    for (int j=0 ; j<matrix.length-i-1; j++) { 
		    	dna_elements +=matrix[matrix.length-j-1][j+i+1];
		    }
		    count_patterns += SearchUtils.getPatternsOfConsecutiveDNAElements(dna_elements);
			if(count_patterns >= ConstantsMutant.QUANTITY_MINIMAL_SEQUENCES_MUTANT)
				return true;		
		}
		logger.debug("SearchDiagonalAsc : no patterns were found");
		return false;
	}

}