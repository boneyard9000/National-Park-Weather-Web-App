package com.techelevator.authentication;

import com.techelevator.dao.model.User;

public interface AuthProvider {
    /**
     * Returns true if a current user is logged in.
     * @return true if user is logged in
     */
    boolean isLoggedIn();

    /**
     * Returns the currently signed in user.
     * @return the currently signed in user
     */
    User getCurrentUser();

    /**
     * Signs in a user using the given username and password
     * @param username the given username
     * @param password the given password
     * @return true if user was successfully signed in
     */
    boolean signIn(String username, String password);

    /**
     * Sign out the currently signed in user
     */
    void logOut();

    /**
     * Register a new user to the system
     * @param username the new user's username
     * @param password the new user's password
     */
    void register(String username, String password);
}
