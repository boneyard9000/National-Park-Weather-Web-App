package com.techelevator.authentication;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.model.User;

/**
 * SessionAuthProvider
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class SessionAuthProvider implements AuthProvider {

    private HttpSession session;
    private UserDao dao;
    public final static String USER_KEY = "appCurrentUser";

    @Autowired
    public SessionAuthProvider(HttpSession session, UserDao dao) {
        this.session = session;
        this.dao = dao;
    }

    @Override
    public boolean isLoggedIn() {
        return (session.getAttribute(USER_KEY) != null);
    }

    @Override
    public User getCurrentUser() {
        return (User) session.getAttribute(USER_KEY);
    }

    @Override
    public boolean signIn(String username, String password) {
        User authenticatedUser = dao.getValidUserWithPassword(username, password);
        if(authenticatedUser != null) {
            session.setAttribute(USER_KEY, authenticatedUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logOut() {
        session.removeAttribute(USER_KEY);
        session.invalidate();
    }

    @Override
    public void register(String username, String password) {
        dao.saveUser(username, password);
    }
}