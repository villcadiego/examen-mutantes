package com.mercadolibre.enums;

/**
 * @author dvillca
 * 
 * Se especifica que los caracteres de la BN solo pueden ser (A,T,C,G).
 *
 */
public enum BaseNitrogenadaEnum {
	
	A("A"),
	T("T"),
	C("C"),
	G("G");
	
	private String caracter;

	BaseNitrogenadaEnum(String caracter) {
		this.caracter = caracter;
	}

	public String getCaracter() {
		return this.caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
}
