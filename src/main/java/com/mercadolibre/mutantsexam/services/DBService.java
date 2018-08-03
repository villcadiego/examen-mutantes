package com.mercadolibre.mutantsexam.services;

import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.model.DnaHuman;

public interface DBService {

    public void addDNASequence(DnaHuman dna) throws MutantDBException;
    public long getCountMutantsDNA() throws MutantDBException;
    long getTotal() throws MutantDBException;
}
