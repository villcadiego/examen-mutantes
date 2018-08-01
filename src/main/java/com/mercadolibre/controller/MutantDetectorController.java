package com.mercadolibre.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.entities.DNARequest;
import com.mercadolibre.exceptions.DNAException;
import com.mercadolibre.exceptions.DNAElementException;
import com.mercadolibre.services.MutantDetectorService;

/**
 * @author dvillca
 */

@RestController
public class MutantDetectorController {

	private static final Logger logger = LogManager.getLogger(MutantDetectorController.class);
	
	@Autowired
	private MutantDetectorService mutantDetector;

	/**
	 * Mutant: #status# HTTP/200
	 * Human : #status# HTTP/403
	 * @param dnaRequest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
	public ResponseEntity<String> isMutant(@RequestBody DNARequest dnaRequest,  HttpServletResponse response) {
		
		ResponseEntity responseEntity = null;
		boolean isMutant = false;
		
		try {
			isMutant = mutantDetector.isMutant(dnaRequest.getDna());
			
			if(isMutant) 
				responseEntity = new ResponseEntity<String>(HttpStatus.OK);
			else
				responseEntity = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			
		} catch (DNAException | DNAElementException e) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			logger.error(e.getMessage(), e);
		}
		return responseEntity;
				
	}	
}
