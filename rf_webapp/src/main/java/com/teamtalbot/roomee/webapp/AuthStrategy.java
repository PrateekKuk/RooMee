package com.teamtalbot.roomee.webapp;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import com.teamtalbot.roomee.webapp.pages.LoginPage;
import com.teamtalbot.roomee.webapp.pages.SecurePage;

public class AuthStrategy implements IAuthorizationStrategy {
	@Override
    public boolean isActionAuthorized(final Component component,
            final Action action) {
        return true;
    }

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
            final Class<T> componentClass) {
        if (!Page.class.isAssignableFrom(componentClass)) {
            return true;
        }

        if (!SecurePage.class.isAssignableFrom(componentClass)) {
            return true;
        }

        if (!RooMeeSession.get().isLoggedIn()) {
            throw new RestartResponseAtInterceptPageException(LoginPage.class);
        }

        return true;
    }

}
