package com.teamtalbot.roomee.pojo;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.teamtalbot.roomee.utils.DBConstants;

public class Profile {
	
	private String name;
	private String username;
	private String email;
	private Location location;
	private String picture;
	private String language;
	private String other;
	private String phone;
	private int age;
	private int minSleep;
	private int maxSleep;
	private int clean;
	private int drink;
	private int party;
	private int maxRent;
	private int minRent;
	private ArrayList<Question> questions;
	private boolean ismoke;
	private boolean usmoke;
	private boolean ipet;
	private boolean upet;
	private boolean hvpet;
	

	
	/**
	 * Defines a user Profile
	 */
	public Profile()
	{
	 this.questions = new ArrayList<Question>();
	}

	/**
	 * Defines a user Profile
	 */
	public Profile(String username,
                   String name,
                   String email,
                   String phone,
                   int    age)
	{
	 this.username = username;
	 this.name = name;
	 this.email = email;
	 this.phone = phone;
	 this.age = age;
	}

	/**
	 * Defines a user Profile, by parsing DBObject
	 */
	public Profile(BasicDBObject profile)
	{
        BasicDBList questionsList;
        BasicDBObject loc;
		name = profile.getString(DBConstants.NAME_KEY);
		username = profile.getString(DBConstants.USERNAME_KEY);
		email= profile.getString(DBConstants.EMAIL_KEY);
		loc = (BasicDBObject) profile.get(DBConstants.LOCATION_KEY);
		picture = profile.getString(DBConstants.PIC_KEY);
		language = profile.getString(DBConstants.LANGUAGE_KEY);
		other = profile.getString(DBConstants.OTHER_KEY);
		phone = profile.getString(DBConstants.PHONE_KEY);
		age = profile.getInt(DBConstants.AGE_KEY);
		minSleep = profile.getInt(DBConstants.MINSLEEP_KEY);
		maxSleep = profile.getInt(DBConstants.MAXSLEEP_KEY);
		clean = profile.getInt(DBConstants.CLEAN_KEY);
		drink = profile.getInt(DBConstants.DRINK_KEY);
		party = profile.getInt(DBConstants.PARTY_KEY);
		maxRent = profile.getInt(DBConstants.MAXRENT_KEY);
		minRent = profile.getInt(DBConstants.MINRENT_KEY);
		questionsList = (BasicDBList) profile.get(DBConstants.MYQS_KEY);
		ismoke = profile.getBoolean(DBConstants.ISMOKE_KEY);
		usmoke = profile.getBoolean(DBConstants.USMOKE_KEY);
		ipet = profile.getBoolean(DBConstants.IPET_KEY);
		upet = profile.getBoolean(DBConstants.UPET_KEY);
		hvpet = profile.getBoolean(DBConstants.HAVEPET_KEY);
		for(int i = 0; i < questionsList.size(); i++)
		{ 
			BasicDBObject obj = (BasicDBObject) questionsList.get(i);
			Question toAdd = new Question(obj.getInt(DBConstants.QID_KEY),obj.getString(DBConstants.QTEXT_KEY));			
		    questions.add(toAdd);
		}
		location = new Location(loc.getString(DBConstants.CITY_KEY),
				                loc.getString(DBConstants.STATE_KEY),
				                loc.getInt(DBConstants.ZIP_KEY));
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Location getLoc() {
		return location;
	}

	public void setLoc(Location loc) {
		this.location = loc;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMinSleep() {
		return minSleep;
	}

	public void setMinSleep(int minSleep) {
		this.minSleep = minSleep;
	}

	public int getMaxSleep() {
		return maxSleep;
	}

	public void setMaxSleep(int maxSleep) {
		this.maxSleep = maxSleep;
	}

	public int getClean() {
		return clean;
	}

	public void setClean(int clean) {
		this.clean = clean;
	}

	public int getDrink() {
		return drink;
	}

	public void setDrink(int drink) {
		this.drink = drink;
	}

	public int getParty() {
		return party;
	}

	public void setParty(int party) {
		this.party = party;
	}

	public int getMaxRent() {
		return maxRent;
	}

	public void setMaxRent(int maxRent) {
		this.maxRent = maxRent;
	}

	public int getMinRent() {
		return minRent;
	}

	public void setMinRent(int minRent) {
		this.minRent = minRent;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public boolean isIsmoke() {
		return ismoke;
	}

	public void setIsmoke(boolean ismoke) {
		this.ismoke = ismoke;
	}

	public boolean isUsmoke() {
		return usmoke;
	}

	public void setUsmoke(boolean usmoke) {
		this.usmoke = usmoke;
	}

	public boolean isIpet() {
		return ipet;
	}

	public void setIpet(boolean ipet) {
		this.ipet = ipet;
	}

	public boolean isUpet() {
		return upet;
	}

	public void setUpet(boolean upet) {
		this.upet = upet;
	}

	public boolean isHvpet() {
		return hvpet;
	}

	public void setHvpet(boolean hvpet) {
		this.hvpet = hvpet;
	}




}
