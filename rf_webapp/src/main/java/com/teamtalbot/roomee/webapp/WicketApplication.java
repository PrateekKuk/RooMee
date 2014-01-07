package com.teamtalbot.roomee.webapp;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.teamtalbot.roomee.webapp.pages.ExplorePage;
import com.teamtalbot.roomee.webapp.pages.FindRoommates;
import com.teamtalbot.roomee.webapp.pages.AskQuestions;
import com.teamtalbot.roomee.webapp.pages.LoginPage;
import com.teamtalbot.roomee.webapp.pages.PersonalityProfile;
import com.teamtalbot.roomee.webapp.pages.RegisterPage;
import com.teamtalbot.roomee.webapp.pages.WhatPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.teamtalbot.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return ExplorePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		mountPage("/index.html", WhatPage.class);
		mountPage("/explore.html",ExplorePage.class);
		mountPage("/login.html", LoginPage.class);
		mountPage("/register.html", RegisterPage.class);
		mountPage("/find.html", FindRoommates.class);
		mountPage("/personalityProfile.html", PersonalityProfile.class);
		mountPage("/askQuestions.html", AskQuestions.class);
	}
	
	public Session newSession(Request request, Response response) {
		return new RooMeeSession(request);
	}
}
