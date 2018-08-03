package com.mercadolibre.mutantsexam.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DnaHuman {

    @Id
    private ObjectId id;

    private String[] sequence;

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

}