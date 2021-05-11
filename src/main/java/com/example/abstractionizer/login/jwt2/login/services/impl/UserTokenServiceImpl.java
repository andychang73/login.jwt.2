package com.example.abstractionizer.login.jwt2.login.services.impl;

import com.example.abstractionizer.login.jwt2.constants.RedisConstant;
import com.example.abstractionizer.login.jwt2.login.services.UserTokenService;
import com.example.abstractionizer.login.jwt2.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt2.utils.JwtUtil;
import com.example.abstractionizer.login.jwt2.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class UserTokenServiceImpl implements UserTokenService {

    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    public static final long tokenValidity = 1;

    @Override
    public Optional<String> generateToken(String username) {

        String token;
        int count = 0;

        while(true){
            count++;
            token = jwtUtil.generateToken(username);
            if(!redisUtil.isKeyExists(token)){
                break;
            }
            token = null;
            if(count >= 3){
                break;
            }
        }
        return Optional.ofNullable(token);
    }

    @Override
    public Optional<String> getOldTokenIfExists(String token) {
        return Optional.ofNullable(redisUtil.get(RedisConstant.getUserLoginToken(token), String.class));
    }

    @Override
    public void setToken(String token, UserInfoVo userInfo) {
        redisUtil.set(RedisConstant.getUserLoginToken(token), userInfo, tokenValidity, TimeUnit.MINUTES);
    }

    @Override
    public Optional<UserInfoVo> getUserInfo(String token) {
        return Optional.ofNullable(redisUtil.get(RedisConstant.getUserLoginToken(token), UserInfoVo.class));
    }

    @Override
    public void setLoggedInUser(String username, String token) {
        redisUtil.set(RedisConstant.getLoggedInUser(username), token, tokenValidity, TimeUnit.MINUTES);
    }

    @Override
    public boolean isUserLoggedIn(String username) {
        return redisUtil.isKeyExists(RedisConstant.getLoggedInUser(username));
    }

    @Override
    public void deleteToken(String token) {
        redisUtil.deleteKey(RedisConstant.getUserLoginToken(token));
    }

    @Override
    public void userLogOut(String username) {
        redisUtil.deleteKey(RedisConstant.getLoggedInUser(username));
    }
}
