package com.mercadolibre.entidades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mercadolibre.constantes.ConstantsMutant;
import com.mercadolibre.interfaces.IBusquedaMutante;
import com.mercadolibre.utils.BusquedaUtils;

public class BusquedaDiagonalAsc implements IBusquedaMutante {
	
	private static final Logger logger = LogManager.getLogger(BusquedaDiagonalAsc.class);

	public BusquedaDiagonalAsc() {
		
	}
	
	/**
	 * Implementacion de buscar mutantes por el metodo diagonal ascendente
	 */
	@Override
	public boolean buscarMutante(char[][] matriz) {
		int cantidad_patrones = 0;
		
		//primera parte diagonal superior
		for (int i = (ConstantsMutant.CANTIDAD_CARACTERES_IGUALES - 1);i < matriz.length ; i++) {
			String base_nitrogenada = "";
		    for (int j = 0; j <= i; j ++) {
		    	base_nitrogenada += matriz[i-j][j];
		    }
		    System.out.println(base_nitrogenada);
		    cantidad_patrones += BusquedaUtils.getCantidadPatronesRepetidos(base_nitrogenada);
			if(cantidad_patrones >= ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA)
				return true;		
		}

		//segunda parte diagonal inferior
		for (int i=0;i < (matriz.length - ConstantsMutant.CANTIDAD_CARACTERES_IGUALES); i++) {
			String base_nitrogenada = "";
		    for (int j=0 ; j<matriz.length-i-1; j++) { 
		    	base_nitrogenada +=matriz[matriz.length-j-1][j+i+1];
		    }
		    System.out.println(base_nitrogenada);
		    cantidad_patrones += BusquedaUtils.getCantidadPatronesRepetidos(base_nitrogenada);
			if(cantidad_patrones >= ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA)
				return true;		
		}
		return false;
	}

}