package com.mercadolibre.mutantsexam.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutantsexam.dao.DataBaseDAO;
import com.mercadolibre.mutantsexam.entities.SearchDiagonalAsc;
import com.mercadolibre.mutantsexam.entities.SearchDiagonalDesc;
import com.mercadolibre.mutantsexam.entities.SearchHorizontal;
import com.mercadolibre.mutantsexam.entities.SearchVertical;
import com.mercadolibre.mutantsexam.enums.DNAElementEnum;
import com.mercadolibre.mutantsexam.exceptions.DNAElementException;
import com.mercadolibre.mutantsexam.exceptions.DNAException;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.interfaces.SearchMutant;
import com.mercadolibre.mutantsexam.services.MutantDetectorService;


@Service("mutantDetectorService")
public class MutantDetectorServiceImpl implements MutantDetectorService {
	
	@Autowired
	private DataBaseDAO dataBaseDAO;
	
	private static final Logger logger = LogManager.getLogger(MutantDetectorServiceImpl.class);
	
	public boolean isMutant(String[] dna) throws DNAElementException, DNAException, MutantDBException {
	
		boolean isMutant = false;
		
		char[][] matrix = this.initializeMatrix(dna); //matrix initialization
		
		List<SearchMutant> estrategias = getSearchStrategies(); //search strategies
		
		for (SearchMutant busquedaStrategy : estrategias) {
				if(busquedaStrategy.searchMutants(matrix)) {
					isMutant = true;
					break;
				}
		}
		logger.info("isMutant: " + isMutant);
		dataBaseDAO.insertDNA(dna, isMutant);
		return isMutant;
	}
	
	
	/**
	 * Specification: En donde recibir�s como par�metro un array de Strings que representan cada fila de una tabla de (NxN)
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
		strategies.add(new SearchDiagonalDesc());
		strategies.add(new SearchHorizontal());
		strategies.add(new SearchVertical());
		return strategies;
	}
}