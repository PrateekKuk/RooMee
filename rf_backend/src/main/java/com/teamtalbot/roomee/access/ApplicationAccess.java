package com.teamtalbot.roomee.access;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.teamtalbot.roomee.pojo.Answer;
import com.teamtalbot.roomee.pojo.Application;
import com.teamtalbot.roomee.pojo.Question;
import com.teamtalbot.roomee.utils.DBConstants;
import com.teamtalbot.roomee.utils.DBUtils;
import com.teamtalbot.roomee.utils.GlobalConstants;

public class ApplicationAccess {
	
	private static final String HOST = "ds029338.mongolab.com";
	private static final String DB_NAME = "roomee";
	private static final int PORT = 29338;
	
	public static List<Application> getApplicationsToUser(String username){
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_APPLICATION);
			
			BasicDBObject query = new BasicDBObject(DBConstants.USERNAME_KEY, username);
			
			DBCursor cursor = coll.find(query);
			List<Application> apps = new ArrayList<Application>();
			while(cursor.hasNext()){
				Application app = new Application((BasicDBObject) cursor.next());
				apps.add(app);
			}
			return apps;
		} catch(Exception e){
			return null;
		} finally{
			if(client != null){
				client.close();
			}
		}
	}
	
	public static boolean acceptApplication(Application appl){
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_APPLICATION);
			
			BasicDBObject query = new BasicDBObject("_id", appl.getId());
			BasicDBObject update = new BasicDBObject();
			update.append("$set", new BasicDBObject(DBConstants.STATUS_KEY, GlobalConstants.ACCEPTED));
			
			WriteResult wr = coll.update(query, update);
			
			return true;
		} catch(Exception e){
			return false;
		} finally{
			if(client != null){
				client.close();
			}
		}
		
	}
	
	public static boolean rejectApplication(Application appl){
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_APPLICATION);
			
			BasicDBObject query = new BasicDBObject("_id", appl.getId());
			BasicDBObject update = new BasicDBObject();
			update.append("$set", new BasicDBObject(DBConstants.STATUS_KEY, GlobalConstants.REJECTED));
			
			WriteResult wr = coll.update(query, update);
			
			return true;
		} catch(Exception e){
			return false;
		} finally{
			if(client != null){
				client.close();
			}
		}
	}
	
	public static boolean createNewApplication(String fromUser, String toUser, List<Question> questions, List<Answer> answers){
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_APPLICATION);
			
			BasicDBObject update = new BasicDBObject();
			update.append(DBConstants.TOUSER_KEY, toUser);
			update.append(DBConstants.FROMUSER_KEY, fromUser);
			update.append(DBConstants.MYQS_KEY, questions);
			update.append(DBConstants.MYAS_KEY, answers);
			
			coll.insert(update);
			
			return true;
		} catch(Exception e){
			return false;
		} finally{
			if(client != null){
				client.close();
			}
		}
		
	}
	
	public static boolean addAnswer(ObjectId id, List<Answer> answers){
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_APPLICATION);
			
			BasicDBObject query = new BasicDBObject();
			query.append(DBConstants.ID_KEY, id);
			
			BasicDBObject update = new BasicDBObject();
			
			for(int i =0; i < answers.size(); i++){
				Answer ans = answers.get(i);
				BasicDBObject ansBson = ans.returnAsDBObject(); 
				update.append("$push", new BasicDBObject(DBConstants.MYQS_KEY, ansBson));
			}
			
			coll.update(query, update);
			
			return true;
		} catch(Exception e){
			return false;
		} finally{
			if(client != null){
				client.close();
			}
		}
		
	}
	
	public static boolean addQuestion(ObjectId id, List<Question> questions){
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_APPLICATION);
			
			BasicDBObject query = new BasicDBObject();
			query.append(DBConstants.ID_KEY, id);
			
			BasicDBObject update = new BasicDBObject();
			
			for(int i =0; i < questions.size(); i++){
				Question q = questions.get(i);
				BasicDBObject qBson = q.returnAsDBObject(); 
				update.append("$push", new BasicDBObject(DBConstants.MYQS_KEY, qBson));
			}
			
			coll.update(query, update);
			
			return true;
		} catch(Exception e){
			return false;
		} finally{
			if(client != null){
				client.close();
			}
		}
	}
}
