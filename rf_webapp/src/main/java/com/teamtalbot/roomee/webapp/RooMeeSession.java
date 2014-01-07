package com.teamtalbot.roomee.webapp;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class RooMeeSession extends WebSession {
	private static final long serialVersionUID = 1L;

    private String username;

    public RooMeeSession(final Request request) {
        super(request);

        // Make sure this session is persisted across requests
        this.bind();
    }

    public boolean isLoggedIn() {
        return this.username != null;
    }

    public void logOut() {
        this.username = null;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public static RooMeeSession get() {
        return (RooMeeSession) Session.get();
    }
}
