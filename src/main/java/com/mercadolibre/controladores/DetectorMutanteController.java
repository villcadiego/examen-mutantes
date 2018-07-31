package com.mercadolibre.controladores;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.entidades.DNARequest;
import com.mercadolibre.exepciones.BaseNitrogenadaException;
import com.mercadolibre.exepciones.ElementoBaseNitrogenadaException;
import com.mercadolibre.servicios.DetectorMutanteService;

@RestController
public class DetectorMutanteController {

	private static final Logger logger = LogManager.getLogger(DetectorMutanteController.class);
	
	@Autowired
	private DetectorMutanteService deterctorService;

	/**
	 * ADN Mutante	: HTTP 200-OK
	 * ADN Humano	: HTTP 403-Forbidden
	 */

	@RequestMapping(value="/mutant", method=RequestMethod.POST)
	public void isMutant(@RequestBody DNARequest dnaRequest,  HttpServletResponse response) {
		boolean isMutant = false;
		try {
			
			isMutant = deterctorService.isMutant(dnaRequest.getDna());
			
			if(isMutant) 
				response.setStatus(HttpServletResponse.SC_OK );
			else
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
		} catch (BaseNitrogenadaException | ElementoBaseNitrogenadaException e2) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} 
	}

	public DetectorMutanteService getDeterctorService() {
		return deterctorService;
	}

	public void setDeterctorService(DetectorMutanteService deterctorService) {
		this.deterctorService = deterctorService;
	}
	
	
	
}
