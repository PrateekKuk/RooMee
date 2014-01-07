package com.teamtalbot.roomee.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamtalbot.roomee.pojo.Question;
import com.teamtalbot.roomee.pojo.QuestionGroup;
import com.teamtalbot.roomee.utils.DBConstants;
import com.teamtalbot.roomee.utils.GlobalConstants;

public class QuestionAccess {
	private static List<QuestionGroup> myQuestionGroupList;
	private static Map<String, QuestionGroup> myQuestionGroupMap; 
	static {
		myQuestionGroupList = new ArrayList<QuestionGroup>();
		myQuestionGroupMap = new HashMap<String, QuestionGroup>();
		
		List<Question> tempList = new ArrayList<Question>();
		Map<String, String> params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_TEXT);
		params.put(GlobalConstants.TEXT_DATACONCEPT, GlobalConstants.TEXT_DC_ALPHA);
		params.put(GlobalConstants.Q_ID, DBConstants.CITY_KEY);
		Question quest = new Question(0, "City", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_TEXT);
		params.put(GlobalConstants.TEXT_DATACONCEPT, GlobalConstants.TEXT_DC_ALPHA);
		params.put(GlobalConstants.Q_ID, DBConstants.STATE_KEY);
		quest = new Question(1, "State", params);
		tempList.add(quest);

		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_SLIDER);
		params.put(GlobalConstants.SLIDER_COUNT, "2");
		params.put(GlobalConstants.SLIDER_MIN, "50");
		params.put(GlobalConstants.SLIDER_MAX, "5000");
		params.put(GlobalConstants.Q_ID, DBConstants.MINRENT_KEY);
		params.put(GlobalConstants.Q_ID_2, DBConstants.MAXRENT_KEY);
		quest = new Question(2, "Price Range", params);
		tempList.add(quest);
		
		QuestionGroup group = new QuestionGroup("Location", "Where do you want to live?", tempList);
		myQuestionGroupList.add(group);
		myQuestionGroupMap.put("Location", group);
		
		tempList = new ArrayList<Question>();
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_SLIDER);
		params.put(GlobalConstants.SLIDER_COUNT, "1");
		params.put(GlobalConstants.SLIDER_MIN, "0");
		params.put(GlobalConstants.SLIDER_MAX, "24");
		params.put(GlobalConstants.Q_ID, DBConstants.MAXSLEEP_KEY);
		quest = new Question(3, "What time do you usually go to sleep?", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_SLIDER);
		params.put(GlobalConstants.SLIDER_COUNT, "1");
		params.put(GlobalConstants.SLIDER_MIN, "0");
		params.put(GlobalConstants.SLIDER_MAX, "24");
		params.put(GlobalConstants.Q_ID, DBConstants.MINSLEEP_KEY);
		quest = new Question(4, "What time do you usually wake up?", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_SLIDER);
		params.put(GlobalConstants.SLIDER_COUNT, "1");
		params.put(GlobalConstants.SLIDER_MIN, "0");
		params.put(GlobalConstants.SLIDER_MAX, "10");
		params.put(GlobalConstants.Q_ID, DBConstants.CLEAN_KEY);
		quest = new Question(5, "How clean do you consider yourself?", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_SLIDER);
		params.put(GlobalConstants.SLIDER_COUNT, "1");
		params.put(GlobalConstants.SLIDER_MIN, "0");
		params.put(GlobalConstants.SLIDER_MAX, "10");
		params.put(GlobalConstants.Q_ID, DBConstants.DRINK_KEY);
		quest = new Question(6, "How much/often do you drink?", params);
		tempList.add(quest);

		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_SLIDER);
		params.put(GlobalConstants.SLIDER_COUNT, "1");
		params.put(GlobalConstants.SLIDER_MIN, "0");
		params.put(GlobalConstants.SLIDER_MAX, "10");
		params.put(GlobalConstants.Q_ID, DBConstants.PARTY_KEY);
		quest = new Question(7, "How often do you have people over?", params);
		tempList.add(quest);

		group = new QuestionGroup("LifeStyle", "Life Style Questions", tempList);
		myQuestionGroupList.add(group);
		myQuestionGroupMap.put("LifeStyle", group);
		
		tempList = new ArrayList<Question>();
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_YES_NO);
		params.put(GlobalConstants.Q_ID, DBConstants.ISMOKE_KEY);
		quest = new Question(8, "Do you smoke?", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_YES_NO);
		params.put(GlobalConstants.Q_ID, DBConstants.USMOKE_KEY);
		quest = new Question(9, "Could you live with someone who smokes?", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_YES_NO);
		params.put(GlobalConstants.Q_ID, DBConstants.IPET_KEY);
		quest = new Question(10, "Do you have pets", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_YES_NO);
		params.put(GlobalConstants.Q_ID, DBConstants.HAVEPET_KEY);
		quest = new Question(11, "Could you live with someone who has pets?", params);
		tempList.add(quest);
		
		params = new HashMap<String, String>();
		params.put(GlobalConstants.QTYPE_KEY, GlobalConstants.QTYPE_OPTIONS);
		params.put(GlobalConstants.Q_ID, DBConstants.LANGUAGE_KEY);
		quest = new Question(12, "Primary Language", params);
		tempList.add(quest);
		
		group = new QuestionGroup("Preferences", "Preferences", tempList);
		myQuestionGroupList.add(group);
		myQuestionGroupMap.put("Preferences", group);
	}
	
	public static List<QuestionGroup> getQuestions(){
		return myQuestionGroupList;
	}
	
	public static QuestionGroup getQuestions(String id){
		return myQuestionGroupMap.get(id);
	}
}
