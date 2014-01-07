package com.teamtalbot.roomee.webapp.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.teamtalbot.roomee.access.UserAccess;
import com.teamtalbot.roomee.pojo.Profile;
import com.teamtalbot.roomee.webapp.RooMeeSession;

public class LoginPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	public LoginPage(final PageParameters parameters) {
		super(parameters);
		
		if (parameters.get("logout") != null && parameters.get("logout").toString() != null && parameters.get("logout").toString() != "null") {
			RooMeeSession.get().logOut();
			setResponsePage(WhatPage.class);
		}
		
		Form<LoginPage> form = new Form<LoginPage>("loginForm", new CompoundPropertyModel<LoginPage>(this)) {
			private static final long serialVersionUID = 1L;
			
			public void onSubmit() {
				Profile prof = UserAccess.authenticateUser(username, password);
				if (prof != null) {
					RooMeeSession.get().setUsername(username);
					setResponsePage(ExplorePage.class);
				}
			}
		};
		add(form);
		form.setOutputMarkupId(true);
		form.setMarkupId("loginForm");
		form.add(new TextField<String>("username").setOutputMarkupId(true).setMarkupId("username"));
		form.add(new PasswordTextField("password").setOutputMarkupId(true).setMarkupId("password"));
    }
}
