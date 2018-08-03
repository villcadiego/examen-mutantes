package com.mercadolibre.mutansexam.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

@Entity(value="dna")
public class Dna {

    @Id
    private ObjectId id;

    @Indexed(options = @IndexOptions(unique = true))
    private String[] sequence;
    
    private boolean mutant;

    public Dna(String[] dna, boolean isMutant) {
        this.sequence = dna;
        this.mutant = isMutant;
    }

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String[] getSequence() {
		return sequence;
	}

	public void setSequence(String[] sequence) {
		this.sequence = sequence;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
}