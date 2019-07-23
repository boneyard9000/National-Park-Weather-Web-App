package com.techelevator.dao;

import com.techelevator.dao.model.User;

public interface UserDao {
    public User saveUser(String userName, String password);
    public User getValidUserWithPassword(String userName, String password);

}
