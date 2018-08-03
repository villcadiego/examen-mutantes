package com.mercadolibre.mutantsexam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutansexam.model.Dna;
import com.mercadolibre.mutantsexam.dao.DataBaseDAO;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Service
public class MongoDBDAOImpl implements DataBaseDAO{
	
	private static Datastore ds;

	private static Datastore getConection() {
		 if( ds == null) {
			 createConection();
		 }
	     return ds;
	}
	
	@Override
	public void insertDNA(String[] dna, boolean isMutant) throws MutantDBException {
		
		Dna object = new Dna(dna, isMutant);
	    try {
	    	MongoDBDAOImpl.getConection().save (object);
	    } catch (MongoException e) {
	        throw new MutantDBException("Error cnnection: " + e.getMessage());
	    }
	}

	@Override
	public long getHumansCount() throws MutantDBException {
        Query<Dna> query = MongoDBDAOImpl.getConection().createQuery(Dna.class);
        query.field("mutant").equal(Boolean.FALSE);
        return  query.count();
	}

	@Override
	public long getMutantsCount() throws MutantDBException {
        Query<Dna> query = MongoDBDAOImpl.getConection().createQuery(Dna.class);
        query.field("mutant").equal(Boolean.TRUE);
	    return query.count();
	}
	
	@Override
	public boolean reset() throws MutantDBException {
		MongoDBDAOImpl.getConection().getCollection(Dna.class).drop();
		return false;
	}
	
	public static void  createConection() {
		String host = 	"ds227168.mlab.com";
		int    port = 	27168;
		String bd	=	"mutantes";
		String user =   "root";
		String pass =   "123456789m";
		
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.writeConcern(WriteConcern.MAJORITY);
        List<MongoCredential> credenciales = new ArrayList<MongoCredential>();
        credenciales.add(MongoCredential.createCredential(user, bd , pass.toCharArray()));
        MongoClient client = new MongoClient(new ServerAddress(host, port), credenciales);
        ds = new Morphia().mapPackage("com.mercadolibre.mutantsexam.model").createDatastore(client, bd);
        ds.ensureIndexes();
	}
}