package com.mercadolibre.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.entidades.BusquedaDiagonalAsc;
import com.mercadolibre.entidades.BusquedaDiagonalDesc;
import com.mercadolibre.entidades.BusquedaHorizontal;
import com.mercadolibre.entidades.BusquedaVertical;
import com.mercadolibre.enums.BaseNitrogenadaEnum;
import com.mercadolibre.exepciones.BaseNitrogenadaException;
import com.mercadolibre.exepciones.ElementoBaseNitrogenadaException;
import com.mercadolibre.interfaces.IBusquedaMutante;
import com.mercadolibre.servicios.IDetectorMutanteService;

public class DetectorMutanteServiceImpl implements IDetectorMutanteService {
	
	private static final Logger LOGGER = LogManager.getLogger(DetectorMutanteServiceImpl.class);
	
	public boolean isMutant(String[] dna) throws ElementoBaseNitrogenadaException, BaseNitrogenadaException {
		
		boolean blnResult = false;
		
		/**valido elementos de las bases nitrogenadas*/
		validarBaseNitrogenada(dna);
		
		char[][] matriz = this.inicializarMatriz(dna);
		
		/** Busqueda de estrategias de busqueda*/
		List<IBusquedaMutante> estrategias = getEstrategiasBusqueda();
		
		/** Busqueda de mutantes por diferentes estrategias */
		for (IBusquedaMutante busquedaStrategy : estrategias) {
			if(busquedaStrategy.buscarMutante(matriz)) {
				return true;
			}
		}
		return blnResult;
	}
	
	
	/**
	 * Objetivo: Inicializar una matriz con los elementos ingresados para poder realizar las operaciones correspondientes
	 * Especificacion: En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN)
	 *
	 * @throws BaseNitrogenadaException 
	 * @throws ElementoBaseNitrogenadaException 
	 */
	
	public char[][] inicializarMatriz(String[] dna) throws BaseNitrogenadaException, ElementoBaseNitrogenadaException{
		
		/**validacion de base nitrogenada*/
		validarBaseNitrogenada(dna);
		
		/**inicializacion de matriz*/
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
	 * Objetivo		 : Evalua todos los elementos de las bases nitrogenadas, y su correcta estructura
	 * Especificacion: "Las letras de los Strings solo pueden ser: (A,T,C,G)."
	 * 
	 * @throws BaseNitrogenadaException
	 * @throws ElementoBaseNitrogenadaException 
	 */
	private void validarBaseNitrogenada(String[] dna) throws BaseNitrogenadaException, ElementoBaseNitrogenadaException {
		
		for (String baseNitrogenada : dna) {
			if(baseNitrogenada.length() != dna.length) {
				throw new BaseNitrogenadaException("Base nitrogenada no válida, los elementos ingresados deben corresponder al rango (NxN)");
			}
		}

		for (String base_nitrogenada : dna) {
			for (char element : base_nitrogenada.toCharArray()) {
				try {
					BaseNitrogenadaEnum.valueOf(String.valueOf(element));
				}catch (IllegalArgumentException e) {
					String mensaje = "El elemento no corresponde a la base nitrogenada (A,T,C,G): ";
					throw new ElementoBaseNitrogenadaException(mensaje + e.getMessage());
				}
			}
		}
	}

	/**
	 * Es posible implementar nuevas estrategias de busqueda, solo es necesario
	 * implementar la interface IBusquedaMutanteStrategy
	 * 
	 * @return List<BusquedaMutanteStrategy>
	 */
	private List<IBusquedaMutante> getEstrategiasBusqueda() {
		List<IBusquedaMutante> estrategias = new ArrayList<>();
		
		estrategias.add(new BusquedaDiagonalAsc());
		LOGGER.debug("Estrategia BusquedaDiagonalAsc");
		
		estrategias.add(new BusquedaDiagonalDesc());
		LOGGER.debug("Estrategia BusquedaDiagonalDesc");
		
		estrategias.add(new BusquedaHorizontal());
		LOGGER.debug("Estrategia BusquedaHorizontal");
		
		estrategias.add(new BusquedaVertical());
		LOGGER.debug("Estrategia BusquedaVertical");
		
		return estrategias;
	}
}