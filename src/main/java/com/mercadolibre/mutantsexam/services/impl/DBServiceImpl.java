package com.mercadolibre.mutantsexam.services.impl;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mercadolibre.mutansexam.model.Dna;
import com.mercadolibre.mutantsexam.exceptions.MutantDBException;
import com.mercadolibre.mutantsexam.services.DBService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;


public class DBServiceImpl implements DBService {

    private static final Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    private Datastore ds;

    public DBServiceImpl() {
        this.createDataStore("localhost", 27017, "mutants");
    }

    public DBServiceImpl(String host, int port) {
        this.createDataStore(host, port, "mutants");
    }

    private void createDataStore(String host, int port, String database) {
    	String mapPackge = "examen-mutantes";
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.writeConcern(WriteConcern.MAJORITY);
        
        ServerAddress address = new ServerAddress(host, port);

        MongoClient client = new MongoClient(address, builder.build());
        Datastore ds = new Morphia().mapPackage(mapPackge).createDatastore(client, database);
        ds.ensureIndexes();
        this.ds = ds;
    }

    
    public void addDNASequence(Dna dna) throws MutantDBException{
        try {
	        } catch (MongoException e) {
	            throw new MutantDBException("Error on database connection: " + e.getMessage());
	        }
    }

    @Override
    public long getCountMutantsDNA() throws  MutantDBException{
        long count = 0;
        try {
            Query<Dna> query = ds.createQuery(Dna.class);
            query.field("mutant").equal(Boolean.TRUE);
            count = query.count();
        } catch(MongoException me) {
        	
            throw new MutantDBException("Error while getting count of mutants dna: " + me.getMessage());
        }
        logger.info("Count of mutants dna founded: " + count);
        return count;
    }

    @Override
    public long getTotal() throws MutantDBException{
        long count = 0;
        try {
            Query<Dna> query = ds.createQuery(Dna.class);
            count = query.count();
        } catch (MongoException me) {
            throw new MutantDBException("Error while getting count of dna analized: " + me.getMessage());
        }
        return count;
    }
}