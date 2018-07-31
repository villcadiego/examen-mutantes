package com.mercadolibre.entidades;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.constantes.ConstantsMutant;
import com.mercadolibre.interfaces.IBusquedaMutante;
import com.mercadolibre.utils.BusquedaUtils;

public class BusquedaVertical implements IBusquedaMutante {	
	
	private static final Logger logger = LogManager.getLogger(BusquedaVertical.class);

	public BusquedaVertical() {
	}
	
	/**
	 * Implementacion de buscar mutantes por el metodo vertical
	 */
	@Override
	public boolean buscarMutante(char[][] matriz) {
		
		int cantidad_patrones = 0;
		for (int i = 0; i < matriz.length; i++) {
			String base_nitrogenada = "";
			for (int j = 0; j < matriz.length; j++) {
				base_nitrogenada += matriz[j][i];
			}
			cantidad_patrones += BusquedaUtils.getCantidadPatronesRepetidos(base_nitrogenada);
			if(cantidad_patrones >= ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA)
				return true;
		}
		
		return false;
	}

}