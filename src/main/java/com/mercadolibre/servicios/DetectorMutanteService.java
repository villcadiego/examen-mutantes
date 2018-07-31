package com.mercadolibre.servicios;

import com.mercadolibre.exepciones.BaseNitrogenadaException;
import com.mercadolibre.exepciones.ElementoBaseNitrogenadaException;

/**
 * Interface generica de Deteccion Mutante, las exceptions
 * @author dvillca
 *
 */

public interface DetectorMutanteService {
	public boolean isMutant(String[] dna) throws BaseNitrogenadaException, ElementoBaseNitrogenadaException;
}
