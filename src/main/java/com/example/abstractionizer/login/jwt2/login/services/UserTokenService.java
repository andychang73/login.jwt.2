package com.example.abstractionizer.login.jwt2.login.services;

import com.example.abstractionizer.login.jwt2.models.vo.UserInfoVo;

import java.util.Optional;

public interface UserTokenService {

    Optional<String> generateToken(String username);

    Optional<String> getOldTokenIfExists(String token);

    void setToken(String token, UserInfoVo userInfo);

    Optional<UserInfoVo> getUserInfo(String token);

    void setLoggedInUser(String username, String token);

    boolean isUserLoggedIn(String username);

    void deleteToken(String token);

    void userLogOut(String username);
}
