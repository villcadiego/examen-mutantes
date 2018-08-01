package com.mercadolibre.utils;

import com.mercadolibre.constants.ConstantsMutant;

/**
 * @author dvillca
 */
public class SearchUtils {

	/**
	 * Returns the number of patterns found in a sequence of DNA elements
	 * The search is independent of the DNA cell (ATGC), the method only evaluates character repetitions
	 * 
	 * @param count_patterns
	 * @return
	 */
	public static int getPatternsOfConsecutiveDNAElements(String dna_elements) {
		
		int count_patterns = 0;
		for (int i = 0; i < dna_elements.length() - (ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA - 1); i++) { //maximum number of iterations limited by the constant
			
			int count_sequences = 0;
			char element 	= dna_elements.charAt(i);
			
			for (int j = 1; j < ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA; j++) { //maximum iteration
				char next_element = dna_elements.charAt(i + j);

				if(element == next_element) {
					count_sequences++;
				}else {
					break;
				}
			}
			if(count_sequences == (ConstantsMutant.QUANTITY_EQUAL_CHARACTERS_DNA - 1)) {
				count_patterns ++;
				i += count_sequences; //the index is modified so as not to repeat the search in the next element.
									  //TODO IMPORTANT: does not allow duplicate patterns in a sequence of type AAAAA (5 characters)
			}
		}
		return count_patterns;
	}
}
