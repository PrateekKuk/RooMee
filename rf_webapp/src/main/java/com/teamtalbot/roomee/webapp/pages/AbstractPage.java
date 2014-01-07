package com.teamtalbot.roomee.webapp.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import com.teamtalbot.roomee.webapp.RooMeeSession;

public class AbstractPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public AbstractPage(final PageParameters parameters) {
		super(parameters);
		
		String username = RooMeeSession.get().getUsername() != null ? RooMeeSession.get().getUsername() : "";
		add(new Label("hiddenuser", username).setOutputMarkupId(true).setMarkupId("hiddenuser"));
		
		WebMarkupContainer loginArea = new WebMarkupContainer("signInArea");
		add(loginArea);
		loginArea.setVisible(!RooMeeSession.get().isLoggedIn());
		
		WebMarkupContainer signOutArea = new WebMarkupContainer("signOutArea");
		add(signOutArea);
		signOutArea.setVisible(RooMeeSession.get().isLoggedIn());
    }
}
