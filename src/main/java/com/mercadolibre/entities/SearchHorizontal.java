package com.mercadolibre.entities;
import com.mercadolibre.constants.ConstantsMutant;
import com.mercadolibre.interfaces.SearchMutant;
import com.mercadolibre.utils.SearchUtils;


/**
 * @author dvillca
 * Concrete Strategy
 */
public class SearchHorizontal implements SearchMutant{
	
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
		
		return false;
	}
}