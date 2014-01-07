package com.teamtalbot.roomee.pojo;

import com.mongodb.BasicDBObject;
import com.teamtalbot.roomee.utils.DBConstants;
 

public class Location {


	private String city;
	private String state;
	private int zip;
	
	public Location()
	{
		
	}
	public Location (String city, String state, int zip)
	{
	 this.city = city;
	 this.state = state;
	 this.zip = zip;
	}
	
	public BasicDBObject returnAsDBObject()
	{
		BasicDBObject result = new BasicDBObject();
		result.append(DBConstants.CITY_KEY, this.city);
		result.append(DBConstants.STATE_KEY, this.state);
		result.append(DBConstants.ZIP_KEY, this.zip);
		return result;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}

}
