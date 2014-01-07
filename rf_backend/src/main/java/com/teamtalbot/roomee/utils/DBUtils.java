package com.teamtalbot.roomee.utils;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DBUtils {
	
	private static final String HOST = "ds029338.mongolab.com";
	private static final String DB_NAME = "roomee";
	private static final int PORT = 29338;
	
	public static MongoClient getMongoClient() throws Exception{
		
		MongoClient client = new MongoClient("ds029338.mongolab.com", 29338);
		
		return client;
	}
	
	public static DB getMongoDB(MongoClient client){
		DB db = client.getDB(DB_NAME);
		db.authenticate("talbot", "pass@word1".toCharArray());
		return db;
	}
	
	public static DBCollection getCollection(DB db, String collection){
		DBCollection coll = db.getCollection(collection);
		return coll;
	}
}
