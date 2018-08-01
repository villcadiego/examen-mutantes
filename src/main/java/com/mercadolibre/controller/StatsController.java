package com.mercadolibre.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.dto.Stats;
import com.mercadolibre.exceptions.StatsException;
import com.mercadolibre.services.StatsService;

/**
 * @author dvillca
 */

@RestController
public class StatsController {

	private static final Logger logger = LogManager.getLogger(StatsController.class);
	
	@Autowired
	private StatsService statsService;

	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stats>  getStats()  {
			Stats stats = null;
			try {
				stats = statsService.getStats();
				return ResponseEntity.ok(stats);
				
			} catch (StatsException e) {
				logger.error(e.getMessage(),e);
			}
			return new ResponseEntity<Stats>(null,new HttpHeaders(),HttpStatus.FORBIDDEN);
		}	
	
}
