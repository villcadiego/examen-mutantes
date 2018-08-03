package com.mercadolibre.mutantsexam.services;

import com.mercadolibre.mutansexam.model.Dna;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;

public interface DBService {

    public void addDNASequence(Dna dna) throws MutantDBException;
    public long getCountMutantsDNA() throws MutantDBException;
    long getTotal() throws MutantDBException;
}
