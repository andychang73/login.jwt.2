package com.example.abstractionizer.login.jwt2.login.services.impl;

import com.example.abstractionizer.login.jwt2.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt2.db.rmdb.mappers.UserMapper;
import com.example.abstractionizer.login.jwt2.enums.ErrorCode;
import com.example.abstractionizer.login.jwt2.exceptions.CustomException;
import com.example.abstractionizer.login.jwt2.login.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        if(userMapper.insert(user) != 1){
            throw new CustomException(ErrorCode.DATA_INSERT_FAILED);
        }
        return user;
    }

    @Override
    public boolean isUserExists(String username) {
        return userMapper.countByUsername(username) > 0;
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.selectByUsername(username).orElseThrow(() -> new CustomException(ErrorCode.INVALID_CREDENTIAL));
    }

    @Override
    public void updateLastLoginTime(Integer userId, Date lastLoginTime) {
        if(userMapper.updateLastLoginTime(userId, lastLoginTime) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void freezeAccount(String username) {
        if(userMapper.updateStatus(username) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
