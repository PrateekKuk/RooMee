package com.teamtalbot.roomee.pojo;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.teamtalbot.roomee.utils.DBConstants;

public class Answer {
	
	private String text;
	private int id;
	private String type;
	private Map<String, String> metaData;
	
	public Answer(){
		
	}
	public Answer(int id, String text){
		this.text = text;
		this.id = id;
	}
	public Answer(int id, String text, Map<String, String> metaData){
		this.text = text;
		this.id = id;
		this.metaData = metaData;
	}
	
	public Answer(BasicDBObject object){
		this.id = object.getInt(DBConstants.ID_KEY);
		this.text = object.getString(DBConstants.MYAS_KEY);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	public BasicDBObject returnAsDBObject()
	{
		BasicDBObject result = new BasicDBObject();
		result.append(DBConstants.QID_KEY, this.id);
		result.append(DBConstants.QTEXT_KEY, this.text);
		return result;
	}
	
}
