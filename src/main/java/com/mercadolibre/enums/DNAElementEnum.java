package com.mercadolibre.enums;

/**
 * @author dvillca
 * the DNA characters can only be of the ATCG type
 */
public enum DNAElementEnum {
	
	A("A"),
	T("T"),
	C("C"),
	G("G");
	
	private String caracter;

	DNAElementEnum(String caracter) {
		this.caracter = caracter;
	}

	public String getCaracter() {
		return this.caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
}
