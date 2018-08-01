package com.mercadolibre.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mercadolibre.entities.SearchDiagonalAsc;
import com.mercadolibre.entities.SearchDiagonalDesc;
import com.mercadolibre.entities.SearchHorizontal;
import com.mercadolibre.entities.SearchVertical;
import com.mercadolibre.enums.DNAElementEnum;
import com.mercadolibre.exceptions.DNAElementException;
import com.mercadolibre.exceptions.DNAException;
import com.mercadolibre.interfaces.SearchMutant;
import com.mercadolibre.services.MutantDetectorService;


@Service("mutantDetectorService")
public class MutantDetectorServiceImpl implements MutantDetectorService {
	
	private static final Logger LOGGER = LogManager.getLogger(MutantDetectorServiceImpl.class);
	
	public boolean isMutant(String[] dna) throws DNAElementException, DNAException {
	
		boolean isMutant = false;
		
		char[][] matrix = this.initializeMatrix(dna); //matrix initialization
		
		List<SearchMutant> estrategias = getSearchStrategies(); //search strategies
		
		for (SearchMutant busquedaStrategy : estrategias) {
			if(busquedaStrategy.searchMutants(matrix)) {
				return true;
			}
		}
		return isMutant;
	}
	
	
	/**
	 * Specification: En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN)
	 *
	 * @throws DNAException 
	 * @throws DNAElementException 
	 */
	
	public char[][] initializeMatrix(String[] dna) throws DNAException, DNAElementException{
		
		validateDNAElements(dna); //validation of DNA elements
		
		char matriz[][] = new char[dna.length][dna.length];
		   for (int fi = 0; fi < dna.length; fi++) {
			   char[] array_char = dna[fi].toCharArray();
			   for (int x = 0; x < array_char.length; x++) {
		         matriz[fi][x] = array_char[x];
		       }
		   }
		   return matriz;
	}
	
	
	/**
	 * Evaluate all elements of DNA and its correct structure
	 * Specification: "Las letras de los Strings solo pueden ser: (A,T,C,G)."
	 * 
	 * @throws DNAException
	 * @throws DNAElementException 
	 */
	private void validateDNAElements(String[] dna) throws DNAException, DNAElementException {
		
		for (String baseNitrogenada : dna) {
			if(baseNitrogenada.length() != dna.length) {
				throw new DNAException("Invalid DNA, the elements entered do not correspond to a matrix (NxN)");
			}
		}

		for (String base_nitrogenada : dna) {
			for (char element : base_nitrogenada.toCharArray()) {
				try {
					DNAElementEnum.valueOf(String.valueOf(element));
				}catch (IllegalArgumentException e) {
					String mensaje = "The element does not correspond to the DNA structure (A,T,C,G): ";
					throw new DNAElementException(mensaje + e.getMessage());
				}
			}
		}
	}

	/**
	 * it is possible to implement new search strategies
	 * @return List<SearchMutant>
	 */
	private List<SearchMutant> getSearchStrategies() {
		List<SearchMutant> strategies = new ArrayList<>();
		
		strategies.add(new SearchDiagonalAsc());
		LOGGER.debug("Strategy SearchDiagonalAsc");
		
		strategies.add(new SearchDiagonalDesc());
		LOGGER.debug("Strategy SearchDiagonalDesc");
		
		strategies.add(new SearchHorizontal());
		LOGGER.debug("Strategy SearchHorizontal");
		
		strategies.add(new SearchVertical());
		LOGGER.debug("Strategy SearchVertical");
		
		return strategies;
	}
}