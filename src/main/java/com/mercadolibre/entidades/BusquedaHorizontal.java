package com.mercadolibre.entidades;
import com.mercadolibre.constantes.ConstantsMutant;
import com.mercadolibre.interfaces.IBusquedaMutante;
import com.mercadolibre.utils.BusquedaUtils;


public class BusquedaHorizontal implements IBusquedaMutante{
	
	public BusquedaHorizontal() {
		
	}
	
	/**
	 * Implementacion de buscar mutantes por el metodo horizontal
	 */
	@Override
	public boolean buscarMutante(char[][] matriz) {
		int cantidad_patrones = 0;
		for (int i = 0; i < matriz.length; i++) {
			String base_nitrogenada = "";
			for (int j = 0; j < matriz.length; j++) {
				base_nitrogenada += matriz[i][j];
			}
			cantidad_patrones += BusquedaUtils.getCantidadPatronesRepetidos(base_nitrogenada);
			if(cantidad_patrones >= ConstantsMutant.CANTIDAD_SECUENCIAS_MINIMA)
				return true;
		}
		
		return false;
	}
}