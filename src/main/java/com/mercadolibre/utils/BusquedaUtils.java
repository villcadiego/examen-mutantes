package com.mercadolibre.utils;

import com.mercadolibre.constantes.ConstantsMutant;

public class BusquedaUtils {

	/**
	 * Metodo que devuelve la cantidad de patrones repetidos encontrados en un una composicion de 
	 * bases nitrogenadas.
	 * 
	 * La busqueda es independiente de la celula de la BN (ATGC), pudiendo permitir nuevas caracteres
	 * por Ej. H-J-K
	 * 
	 * BN>> Base nitrogenada
	 * 
	 * @param base_nitrogenada
	 * @return
	 */
	public static int getCantidadPatronesRepetidos(String composicion_BN) {
		
		int cantidad_repeticiones = 0;
		for (int i = 0; i < composicion_BN.length() - (ConstantsMutant.CANTIDAD_CARACTERES_IGUALES - 1); i++) {
			
			int cantidad_consecutivos = 0;
			char caracter_origen 	= composicion_BN.charAt(i);
			
			for (int j = 1; j < ConstantsMutant.CANTIDAD_CARACTERES_IGUALES; j++) {
				char caracter_siguiente = composicion_BN.charAt(i + j);
				
				if(caracter_origen == caracter_siguiente) {
					cantidad_consecutivos++;
				}else {
					break;
				}
			}
			if(cantidad_consecutivos == (ConstantsMutant.CANTIDAD_CARACTERES_IGUALES - 1)) {
				cantidad_repeticiones ++;
				i += cantidad_consecutivos;
			}
		}
		return cantidad_repeticiones;
	}
}
