package com.teamtalbot.roomee.utils;

import java.util.HashMap;
import java.util.Map;

public class DBConstants {
	public static final String COLLECTION_LOGIN = "login";
	public static final String COLLECTION_PROFILE = "profile";
	public static final String COLLECTION_APPLICATION = "application";
	
	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	public static final String NAME_KEY = "name";
	public static final String AGE_KEY = "age";
	public static final String PIC_KEY = "pic";
	public static final String ISMOKE_KEY = "ismoke";
	public static final String USMOKE_KEY = "usmoke";
	public static final String EMAIL_KEY = "email";
	public static final String IPET_KEY = "ipet";
	public static final String HAVEPET_KEY = "hvpet";
	public static final String UPET_KEY = "upet";
	public static final String LOCATION_KEY = "loc";
	public static final String CITY_KEY = "city";
	public static final String ZIP_KEY = "zip";
	public static final String STATE_KEY = "state";
	public static final String MINRENT_KEY = "minrent";
	public static final String MAXRENT_KEY = "maxrent";
	public static final String MYQS_KEY = "myqs";
	public static final String QID_KEY = "qid";
	public static final String QTEXT_KEY = "qtext";
	public static final String MINSLEEP_KEY = "minsl";
	public static final String MAXSLEEP_KEY = "maxsl";
	public static final String CLEAN_KEY = "clean";
	public static final String DRINK_KEY = "drink";
	public static final String PARTY_KEY = "party";
	public static final String LANGUAGE_KEY = "language";
	public static final String OTHER_KEY = "other";
	public static final String PHONE_KEY = "phone";
	public static final String FROMUSER_KEY = "fusername";
	public static final String TOUSER_KEY = "tusername";
	public static final String MYAS_KEY = "myas";
	public static final String STATUS_KEY = "status";
	public static final String ID_KEY = "id";
	

	public static Map<String, String> DATA_TYPES;
	static {
		DATA_TYPES = new HashMap<String, String>();
		DATA_TYPES.put(USERNAME_KEY, "STRING");
		DATA_TYPES.put(PASSWORD_KEY, "STRING");
		DATA_TYPES.put(NAME_KEY, "STRING");
		DATA_TYPES.put(PIC_KEY, "STRING");
		DATA_TYPES.put(ISMOKE_KEY, "BOOLEAN");
		DATA_TYPES.put(USMOKE_KEY, "BOOLEAN");
		DATA_TYPES.put(EMAIL_KEY, "STRING");
		DATA_TYPES.put(IPET_KEY, "BOOLEAN");
		DATA_TYPES.put(HAVEPET_KEY, "BOOLEAN");
		DATA_TYPES.put(UPET_KEY, "BOOLEAN");
		DATA_TYPES.put(LOCATION_KEY, "LOCATION");
		DATA_TYPES.put(CITY_KEY, "STRING");
		DATA_TYPES.put(ZIP_KEY, "STRING");
		DATA_TYPES.put(STATE_KEY, "STRING");
		DATA_TYPES.put(MINRENT_KEY, "INT");
		DATA_TYPES.put(MAXRENT_KEY, "INT");
		DATA_TYPES.put(MYQS_KEY, "QUESTION");
		DATA_TYPES.put(QID_KEY, "INT");
		DATA_TYPES.put(QTEXT_KEY, "STRING");
		DATA_TYPES.put(MINSLEEP_KEY, "INT");
		DATA_TYPES.put(MAXSLEEP_KEY, "INT");
		DATA_TYPES.put(CLEAN_KEY, "INT");
		DATA_TYPES.put(DRINK_KEY, "INT");
		DATA_TYPES.put(PARTY_KEY, "INT");
		DATA_TYPES.put(LANGUAGE_KEY, "STRING");
		DATA_TYPES.put(PHONE_KEY, "STRING");
	}
	
	
}
