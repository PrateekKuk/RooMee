package com.teamtalbot.roomee.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.teamtalbot.roomee.access.QuestionAccess;
import com.teamtalbot.roomee.access.UserAccess;
import com.teamtalbot.roomee.pojo.Profile;
import com.teamtalbot.roomee.pojo.QuestionGroup;
import com.teamtalbot.roomee.utils.DBConstants;
import com.teamtalbot.roomee.utils.DBUtils;


@Path("/serv")
public class WebService {
	private static final String DB_NAME = "roomee";
	
	@GET
	@Path("addUser")
	@Produces("text/plain")
	public String register(@QueryParam("username") String username,
			@QueryParam("password") String password,
			@QueryParam("name") String name,
			@QueryParam("email") String email,
			@QueryParam("phone") String phone,
			@QueryParam("age") int age){
		
		System.out.println("USERNAME = " + username);
		boolean registered = UserAccess.registerLogin(username, password, name, email, phone, age);
		
		return String.valueOf(registered);	
		//return "false";
	}
	
	@GET
	@Path("isValidUser")
	@Produces("text/plain")
	public static String isValidLogin(@QueryParam("username") String username,
								@QueryParam("password") String password){
		
		Profile profile = UserAccess.authenticateUser(username, password);
		if (profile != null) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(profile);
			return jsonString;
		} else {
			return "false";
		}	
	}
	
	@GET
	@Path("getQuestionGroups")
	@Produces("text/plain")
	public static String getQuestionGroups() {
		List<QuestionGroup> questions = QuestionAccess.getQuestions();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(questions);
		
		return jsonString;	
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("updateAttributes")
	public static String updateAttributes(@QueryParam("username") String username, @QueryParam("attributes") String attributes){
		
		//JSONObject obj = new JSONObject(attributes);
		Map<String, Object> map = (Map<String, Object>)JSONValue.parse(attributes);
		UserAccess.setUserAttributes(username, map);
		return "";
	}	
	
	@GET
	@Path("setAnswers")
	@Produces("text/plain")
	public static String setAnswers(@QueryParam("groupId") String groupId, @QueryParam("answers") String answers){
		
		//TODO: Complete		
		return "false";	
	}

	@GET
	@Path("getMatchesForUser")
	@Produces("text/plain")
	public static String getMatchesForUser( @QueryParam("username") String username){
				
		return UserAccess.findMatchForUser(username).toString();
	}
}
