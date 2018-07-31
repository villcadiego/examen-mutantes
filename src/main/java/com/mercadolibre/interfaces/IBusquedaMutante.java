package com.mercadolibre.interfaces;

/**
 * Se utiliza el patron de diseño Strategy para poder utilizar distintas estrategias de busqueda,
 * solo es necesario implementar esta interfaz para poder analizar por diferentes criterios la busqueda de mutantes
 * @author dvillca
 */
public interface IBusquedaMutante {
	
	public boolean buscarMutante(char[][] matriz);
}