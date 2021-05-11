package com.example.abstractionizer.login.jwt2.login.services;

import com.example.abstractionizer.login.jwt2.db.rmdb.entities.User;

import java.util.Date;

public interface UserService {

    User create(User user);

    boolean isUserExists(String username);

    User getUserByName(String username);

    void updateLastLoginTime(Integer userId, Date lastLoginTime);
}
