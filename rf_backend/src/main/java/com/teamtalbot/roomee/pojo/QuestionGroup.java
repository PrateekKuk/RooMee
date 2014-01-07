package com.teamtalbot.roomee.pojo;

import java.util.List;

public class QuestionGroup {
	private String title;
	private String idTag;

	private List<Question> questions;
	
	public QuestionGroup(){}
	
	public QuestionGroup(String idTag, String title, List<Question> questions) {
		this.idTag = idTag;
		this.title = title;
		this.questions = questions;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public String getIdTag() {
		return idTag;
	}

	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}
}
