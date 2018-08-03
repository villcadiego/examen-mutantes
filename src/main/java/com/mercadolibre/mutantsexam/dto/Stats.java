package com.mercadolibre.mutantsexam.dto;

/**
 * @author dvillca
 */

public class Stats {
	
	private long count_mutant_dna;
	private long count_human_dna;
    private double ratio;

    public Stats(long mutants, long humans) {
        this.count_mutant_dna = mutants;
        this.count_human_dna = humans;
    }

	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public long getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public double getRatio() {
	    long total = count_human_dna + count_mutant_dna;
	    if (total == 0) {
	        return 0.0;
	    }
	    double result = (1.0 * count_mutant_dna / total);
	    
	    return (double) Math.round(result * 100) / 100;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
