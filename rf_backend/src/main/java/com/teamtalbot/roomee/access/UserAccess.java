package com.teamtalbot.roomee.access;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.teamtalbot.roomee.pojo.Profile;
import com.teamtalbot.roomee.utils.DBConstants;
import com.teamtalbot.roomee.utils.DBUtils;

public class UserAccess {
	
	public static boolean registerLogin(String username, String password,
			String name, String email, String phone, int age) {

		MongoClient client = null;
		
		try {

			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);

			DBCollection coll = DBUtils.getCollection(db,
					DBConstants.COLLECTION_LOGIN);

			if (doesUserNameExist(username)) {
				return false;
			}

			
			coll.insert(new BasicDBObject("username", username).append(
							"password", password));

			coll = DBUtils.getCollection(db, DBConstants.COLLECTION_PROFILE);
			BasicDBObject object = new BasicDBObject();
			object.append(DBConstants.USERNAME_KEY, username);
			object.append(DBConstants.NAME_KEY, name);
			object.append(DBConstants.EMAIL_KEY, email);
			object.append(DBConstants.PHONE_KEY, phone);
			object.append(DBConstants.AGE_KEY, age);
			object.append(DBConstants.MINSLEEP_KEY, 0);
			object.append(DBConstants.MAXSLEEP_KEY, 2000);
			object.append(DBConstants.CLEAN_KEY, -1);
			object.append(DBConstants.DRINK_KEY, -1);
			object.append(DBConstants.PARTY_KEY, -1);
			object.append(DBConstants.PIC_KEY, "");
			object.append(DBConstants.LANGUAGE_KEY, "English");
			object.append(DBConstants.MINRENT_KEY, 0);
			object.append(DBConstants.MAXRENT_KEY, 1000000);
			object.append(DBConstants.ISMOKE_KEY, false);
			object.append(DBConstants.USMOKE_KEY, false);
			object.append(DBConstants.IPET_KEY, false);
			object.append(DBConstants.UPET_KEY, false);
			object.append(DBConstants.HAVEPET_KEY, false);
			BasicDBObject loc = new BasicDBObject();
			loc.append(DBConstants.CITY_KEY, "");
			loc.append(DBConstants.STATE_KEY, "MD");
			loc.append(DBConstants.ZIP_KEY, 0);
			object.append(DBConstants.LOCATION_KEY, loc);
			object.append(DBConstants.MYQS_KEY, new ArrayList<Object>());

			coll.insert(object);			
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			if(client!=null){
				client.close();
			}
		}
		return true;
	}

	public static boolean doesUserNameExist(String username) {
		MongoClient client = null;
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db,
					DBConstants.COLLECTION_PROFILE);

			BasicDBObject query = new BasicDBObject("username", username);
			DBCursor cursor = coll.find(query);
			if (cursor.count() >= 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		} finally {
			if (client != null) {
				client.close();
			}
		}

	}

	public static boolean setUserAttributes(String username, Map<String, Object> params){
		MongoClient client = null;
		if (params != null) {
			try {
				client = DBUtils.getMongoClient();
				DB db = DBUtils.getMongoDB(client);
				DBCollection coll = DBUtils.getCollection(db,
						DBConstants.COLLECTION_PROFILE);
	
				BasicDBObject userQuery = new BasicDBObject(
						DBConstants.USERNAME_KEY, username);
				BasicDBObject update = new BasicDBObject();
				for (String key : params.keySet()) {
					String dataType = DBConstants.DATA_TYPES.get(key);
					if (dataType == null) {
						continue;
					} else if (dataType.equals("STRING")) {
						String value = (String) params.get(key);
						update.append(key, value);
					} else if (dataType.equals("INT")) {
						int value = 0;
						try {
							value = (Integer) params.get(key);
						} catch (Exception e) {
							try {
								value = Integer.valueOf((String) params.get(key));
							} catch (Exception e2) {
								
							}
						}
						
						update.append(key, value);
					} else if (dataType.equals("QUESTION")) {
						BasicDBObject value = null;
						update.put( "$push", new BasicDBObject( DBConstants.MYQS_KEY, value ) );
	//					update.append(key, val)
					} else if(dataType.equals("LOCATION")){
						BasicDBObject value = null;
						update.put( "$push", new BasicDBObject( DBConstants.LOCATION_KEY, value ) );
					} else if (dataType.equals("BOOLEAN")) {
						Boolean value = false;
						try {
							value = (Boolean) params.get(key);
						} catch (Exception e) {
							try {
								value = Boolean.valueOf((String) params.get(key));
							} catch (Exception e2) {
								
							}
						}
						
						update.append(key, value != null ? value.booleanValue() : false);
					}
				}
				coll.update(userQuery, new BasicDBObject("$set", update));
				System.out.println("UPDATE = " + update);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if(client != null){
					client.close();
				}
			}
		} else {
			return false;
		}
	}

	public static Profile authenticateUser(String username,
			String password) {
		MongoClient client = null;

		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db,
					DBConstants.COLLECTION_LOGIN);

			BasicDBObject query = new BasicDBObject("username", username);
			query.append("password", password);
			DBCursor cursor = coll.find(query);
			if (cursor.count() == 0) {
			  return null;
			}

			coll = DBUtils.getCollection(db, DBConstants.COLLECTION_PROFILE);
			query = new BasicDBObject(DBConstants.USERNAME_KEY, username);
			cursor = coll.find(query);
			if (cursor.hasNext()) {
				return new Profile((BasicDBObject) cursor.next());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (client != null) {
				client.close();
			}
		}

	}
	public static Profile returnProfileByUserName(String username) 
	{
		MongoClient client = null;

		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_PROFILE);

			BasicDBObject query = new BasicDBObject(DBConstants.USERNAME_KEY, username);
			DBCursor cursor = coll.find(query);
			if (cursor.hasNext()) {
				return new Profile((BasicDBObject) cursor.next());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
	public static BasicDBList searchDbByKey(String key, String value)
	{
		MongoClient client = null;
        BasicDBList results = new BasicDBList();

        
        
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_PROFILE);
      
			BasicDBObject query = new BasicDBObject(key, value);
			DBCursor cursor = coll.find(query);
			while (cursor.hasNext()) {
				results.add(cursor.next());
			}
			return results;
            
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
	public static BasicDBList findMatchForUser(String username)
	{
		//username pic path 
		MongoClient client = null;
        BasicDBList results = new BasicDBList();
        //Compatibility Rating Map
           
        TreeMap<String,Map> sorted_map= new TreeMap<String,Map>();
        
		try {
			client = DBUtils.getMongoClient();
			DB db = DBUtils.getMongoDB(client);
			DBCollection coll = DBUtils.getCollection(db, DBConstants.COLLECTION_PROFILE);
			Profile currentUser = returnProfileByUserName(username);
			System.out.println("STATE:" + currentUser.getLoc().getState());
			BasicDBObject query = new BasicDBObject(DBConstants.LOCATION_KEY + "." + DBConstants.STATE_KEY, currentUser.getLoc().getState());
			DBCursor cursor = coll.find(query);
			System.out.println("SIZE" + cursor.size());
			while (cursor.hasNext()) {
				  BasicDBObject obj = (BasicDBObject) cursor.next(); 
			  
			    		  int score = 0;
			    		  Profile toCompare = returnProfileByUserName(obj.getString(DBConstants.USERNAME_KEY));			    		  		 
			    	
			    		  if (toCompare.getLanguage().equals(currentUser.getLanguage()))
			    		  {
			    			  score += 10;
			    		  }
			    		  if (toCompare.getLoc().getCity().equals(currentUser.getLoc().getCity()))
			    		  {
			    			  score += 10;
			    		  }
			    		  if (toCompare.isIsmoke())
			    		  {
			    			  if(!currentUser.isUsmoke())
			    			  score -= 5000;
			    			  if(currentUser.isIsmoke())
				    			  score +=5;
			    		  }
			    		  if (toCompare.isIpet())
			    		  {
			    			  if(!currentUser.isUpet())
			    			  score -= 5000;
			    			  if(currentUser.isIpet())
				    			  score +=5;
			    		  }
			    		  if (toCompare.isHvpet())
			    		  {
			    			  if(!currentUser.isUpet())
			    			  score -= 5000;
			    			  if(currentUser.isIpet())
				    			  score +=5;
			    		  }
			    		  score += (15 -(2* Math.abs(toCompare.getClean()-currentUser.getClean())));
			    		  score += (15 -(2* Math.abs(toCompare.getDrink()-currentUser.getDrink())));
			    		  score += (15 -(2* Math.abs(toCompare.getParty()-currentUser.getParty())));
			    		  score += (15 -(Math.abs(toCompare.getMinSleep()-currentUser.getMinSleep())));
			    		  score += (15 -(Math.abs(toCompare.getMaxSleep()-currentUser.getMaxSleep())));
			    		  score += (15 -(Math.abs(toCompare.getAge()-currentUser.getAge())));
			    		  score += (15 -(Math.abs(toCompare.getMinRent()/100-currentUser.getMinRent()/100)));
			    		  score += (15 -(Math.abs(toCompare.getMaxRent()/100-currentUser.getMaxRent()/100)));
			    		  

			    		  
			    		  HashMap<String,String> toAdd = new  HashMap <String,String>();
			    		    toAdd.put(DBConstants.USERNAME_KEY, toCompare.getUsername());
				    		toAdd.put(DBConstants.NAME_KEY, toCompare.getName());
				    		toAdd.put(DBConstants.PIC_KEY, toCompare.getPicture());
				    		toAdd.put(DBConstants.USERNAME_KEY, toCompare.getUsername());
				    		toAdd.put(DBConstants.OTHER_KEY, toCompare.getOther());	
				    		toAdd.put("score", new Integer(score).toString() );
			    		 
				    		sorted_map.put( score + toCompare.getUsername(), toAdd);
			    		  
			  
			}

			int counter = 0;
			for (String key :sorted_map.keySet() )
			{ 
				    if(counter< 8 ){
					results.add(sorted_map.get(key));
		   		  	counter++;		   		  	
				    }
				    else
				    	break;				
			}
			System.out.println("RESULTS SIZE" + results.size());
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
	
}


