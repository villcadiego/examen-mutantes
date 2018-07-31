package com.mercadolibre.main;

import com.mercadolibre.exepciones.BaseNitrogenadaException;
import com.mercadolibre.exepciones.ElementoBaseNitrogenadaException;
import com.mercadolibre.servicios.impl.DetectorMutanteServiceImpl;

public class ArrayMain {

	public static void main(String[] args) throws ElementoBaseNitrogenadaException, BaseNitrogenadaException {
		   
//			String[] dna_humano   = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
			String[] dna_humano2   = {"ATGCGAATGCGA","ATGCGAATGCGA","TTATTTATGCGA","AGACGGATGCGA","GCGTCAATGCGA","TCACTGATGCGA","ATGCGAATGCGA","ATGCGAATGCGA","TTATTTATGCGA","AGACGGATGCGA","GCGTCAATGCGA","TCACTGATGCGA"};
//			String[] dna_mutante1 = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
//			String[] dna_mutante2 = {"AAAAGA","GGTTTT","AAAAGT","AGAAGG","CCCTTA","TTTTTG"};
//			String[] dna_mutante3 = {"ATAAAG","AAGTGC","ATTTTG","ATTTGG","CACCTA","CAATTT"};
//			String[] dna_mutante4 = {"AGACGG","AAGTGC","ATTTGG","AAGTGC","AGCCCA","ATTTGG"};
		   
			DetectorMutanteServiceImpl impl = new DetectorMutanteServiceImpl();
			System.out.println("DNA1 Es mutante? " + impl.isMutant(dna_humano2) + "\n");
//			System.out.println("DNA2 Es mutante? " + impl.isMutant(dna_mutante1)+ "\n");
//			System.out.println("DNA3 Es mutante? " + impl.isMutant(dna_mutante2)+ "\n");
//			System.out.println("DNA4 Es mutante? " + impl.isMutant(dna_mutante3)+ "\n");
//			System.out.println("DNA5 Es mutante? " + impl.isMutant(dna_mutante4)+ "\n");
	}
}
