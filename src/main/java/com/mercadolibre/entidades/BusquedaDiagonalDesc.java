package com.mercadolibre.entidades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.constantes.ConstantsMutant;
import com.mercadolibre.interfaces.IBusquedaMutante;
import com.mercadolibre.utils.BusquedaUtils;

public class BusquedaDiagonalDesc implements IBusquedaMutante {	
	
	private static final Logger LOGGER = LogManager.getLogger(BusquedaDiagonalDesc.class);

	public BusquedaDiagonalDesc() {
	}
	
	/**
	 * Implementacion de buscar mutantes por el metodo diagonal descendente
	 */
	@Override
	public boolean buscarMutante(char[][] matriz) {
		int cantidad_patrones = 0;
		
		for (int i = (matriz.length - ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA - 2) ; i >= 0 ; i--) {
			String base_nitrogenada = "";
		    for (int j = 0; j < (matriz.length - i); j ++) {
		    	base_nitrogenada += matriz[i + j][j];
		    	LOGGER.debug("Evaluando: ["+(i+j) +"],["+j+"]");
		    }
		    System.out.println(base_nitrogenada);
		    cantidad_patrones += BusquedaUtils.getCantidadPatronesRepetidos(base_nitrogenada);
			if(cantidad_patrones >= ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA)
				return true;		
		}

		for (int i = 0; i < (matriz.length - ConstantsMutant.CANTIDAD_CARACTERES_IGUALES); i++) {
			String base_nitrogenada = "" ;
		    for (int j=0 ; j < matriz.length -i -1; j++) { 
		    	LOGGER.debug("Evaluando: [\"+(j) +\"],[\"+(j+i+1)+\"]");
		    	base_nitrogenada +=matriz[j][j+i+1];
		    }
		    System.out.println(base_nitrogenada);
		    cantidad_patrones += BusquedaUtils.getCantidadPatronesRepetidos(base_nitrogenada);
			if(cantidad_patrones >= ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA)
				return true;		
		}
		return false;
	}

}