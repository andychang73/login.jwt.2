package com.example.abstractionizer.login.jwt2.constants;

public class RedisConstant {

    public static final String USER_LOGIN_TOKEN = "LOGIN:JWT:TOKEN:%s";
    public static final String LOGGED_IN_USER =  "LOGGED:IN:USER:%s";
    public static final String USER_LOGIN_FAILURE_COUNT = "LOGIN:FAILURE:COUNT:%s";

    public static String getUserLoginToken(String token){
        return String.format(USER_LOGIN_TOKEN, token);
    }

    public static String getLoggedInUser(String username){
        return String.format(LOGGED_IN_USER, username);
    }

    public static String getUserLoginFailureCount(String username){
        return String.format(USER_LOGIN_FAILURE_COUNT, username);
    }
}
