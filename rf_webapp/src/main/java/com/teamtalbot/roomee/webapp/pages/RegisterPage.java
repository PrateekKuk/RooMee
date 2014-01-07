package com.teamtalbot.roomee.webapp.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.teamtalbot.roomee.access.UserAccess;
import com.teamtalbot.roomee.pojo.Profile;
import com.teamtalbot.roomee.webapp.RooMeeSession;

public class RegisterPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	public RegisterPage(final PageParameters parameters) {
		super(parameters);
		
		Form<RegisterPage> form = new Form<RegisterPage>("registerForm", new CompoundPropertyModel<RegisterPage>(this)) {
			private static final long serialVersionUID = 1L;
			
			public void onSubmit() {
				Profile prof = UserAccess.authenticateUser(username, password);
				if (prof != null) {
					RooMeeSession.get().setUsername(username);
					setResponsePage(PersonalityProfile.class);
				}
			}
		};
		add(form);
		form.setOutputMarkupId(true);
		form.setMarkupId("registerForm");
		form.add(new TextField<String>("username").setOutputMarkupId(true).setMarkupId("username"));
		form.add(new PasswordTextField("password").setOutputMarkupId(true).setMarkupId("password"));
    }
}
