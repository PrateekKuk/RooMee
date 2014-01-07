package com.teamtalbot.roomee.pojo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.teamtalbot.roomee.utils.DBConstants;

public class Application {
	private String fromUsername;
	private String toUsername;
	private List<Question> myQuestions;
	private List<Answer> myAnswers;
	private String status;
	private ObjectId id;
	
	public Application(){}
	
	public Application(ObjectId id, String fromUser, String toUser, String status, List<Question> questions, List<Answer> answers){
		this.fromUsername = fromUser;
		this.toUsername = toUser;
		this.myQuestions = questions;
		this.myAnswers = answers;
		this.id = id;
		this.status = status;
	}
	
	public Application(BasicDBObject object){
		this.fromUsername = object.getString(DBConstants.FROMUSER_KEY);
		this.toUsername = object.getString(DBConstants.TOUSER_KEY);
		this.status = object.getString(DBConstants.STATUS_KEY);
		this.id = object.getObjectId("_id");
		this.myAnswers = new ArrayList<Answer>();
		this.myQuestions = new ArrayList<Question>();
		
		BasicDBList quests = (BasicDBList) object.get(DBConstants.MYQS_KEY);
		for(int i = 0; i < quests.size(); i++){
			BasicDBObject qbson = (BasicDBObject) quests.get(i);
			Question q = new Question(qbson);
			this.myQuestions.add(q);
		}
		
		BasicDBList anslist = (BasicDBList) object.get(DBConstants.MYAS_KEY);
		for(int i = 0; i < anslist.size(); i++){
			BasicDBObject abson = (BasicDBObject) quests.get(i);
			Answer a = new Answer(abson);
			this.myAnswers.add(a);
		}
	}
	
	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public List<Question> getMyQuestions() {
		return myQuestions;
	}

	public void setMyQuestions(List<Question> myQuestions) {
		this.myQuestions = myQuestions;
	}

	public List<Answer> getMyAnswers() {
		return myAnswers;
	}

	public void setMyAnswers(List<Answer> myAnswers) {
		this.myAnswers = myAnswers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
}
