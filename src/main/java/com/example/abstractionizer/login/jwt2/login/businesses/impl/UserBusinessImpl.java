package com.example.abstractionizer.login.jwt2.login.businesses.impl;

import com.example.abstractionizer.login.jwt2.constants.RedisConstant;
import com.example.abstractionizer.login.jwt2.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt2.db.rmdb.entities.VerificationToken;
import com.example.abstractionizer.login.jwt2.enums.ErrorCode;
import com.example.abstractionizer.login.jwt2.exceptions.CustomException;
import com.example.abstractionizer.login.jwt2.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt2.login.services.UserService;
import com.example.abstractionizer.login.jwt2.login.services.UserTokenService;
import com.example.abstractionizer.login.jwt2.login.services.VerificationTokenService;
import com.example.abstractionizer.login.jwt2.models.bo.UserLoginBo;
import com.example.abstractionizer.login.jwt2.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt2.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt2.utils.DateUtil;
import com.example.abstractionizer.login.jwt2.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class UserBusinessImpl implements UserBusiness {

    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private final UserTokenService userTokenService;
    private final Util util;


    @Override
    public String register(UserRegisterBo bo) {
        if(userService.isUserExists(bo.getUsername())){
            throw new CustomException(ErrorCode.USERNAME_EXISTS);
        }
        if(verificationTokenService.isUsernameExists(bo.getUsername())){
            throw new CustomException(ErrorCode.USERNAME_EXISTS);
        }
        String uuid = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(getVerificationToken(bo, uuid));

        return getValidationUrl(uuid);
    }

    @Override
    public UserInfoVo accountValidation(String uuid) {
        return getUserInfo(userService.create(getUser(verificationTokenService.getVerificationToken(uuid))));

    }

    @Override
    public String login(UserLoginBo bo) {

        if(userTokenService.isUserLoggedIn(bo.getUsername())){
            throw new CustomException(ErrorCode.USER_LOGGED_IN);
        }

        User user = isAccountValid(bo.getUsername());

        checkPassword(bo.getPassword(), user.getPassword());

        final String token = userTokenService.generateToken(bo.getUsername()).orElseThrow(() -> new RuntimeException("Failed to generate token"));
        Optional<String> oldToken = userTokenService.getOldTokenIfExists(token);
        oldToken.ifPresent(t -> userTokenService.deleteToken(RedisConstant.getUserLoginToken(t)));

        UserInfoVo userInfoVo = new UserInfoVo()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone());

        userTokenService.setLoggedInUser(user.getUsername(), token);
        userTokenService.setToken(token, userInfoVo);
        userService.updateLastLoginTime(user.getId(), new Date());
        return token;
    }

    @Override
    public void logOut(String token) {
        Optional<UserInfoVo> userInfoVo = userTokenService.getUserInfo(token);
        userInfoVo.ifPresent(u -> {
            userTokenService.deleteToken(token);
            userTokenService.userLogOut(u.getUsername());
        });
    }

    private void checkPassword(String enteredPassword, String password){
        if(!util.md5(enteredPassword).equals(password)){
            throw new CustomException(ErrorCode.INVALID_CREDENTIAL);
        }
    }

    private User isAccountValid(String username){
        User user = userService.getUserByName(username);
        if(user.getStatus() != 1){
            throw new CustomException(ErrorCode.ACCOUNT_FROZEN);
        }
        return user;
    }

    private UserInfoVo getUserInfo(User user){
        return new UserInfoVo()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone());
    }

    private User getUser(VerificationToken token){
        return new User()
                .setUsername(token.getUsername())
                .setPassword(token.getPassword())
                .setEmail(token.getEmail())
                .setPhone(token.getPhone());
    }

    private VerificationToken getVerificationToken(UserRegisterBo bo, String uuid){
        return new VerificationToken()
                .setId(uuid)
                .setUsername(bo.getUsername())
                .setPassword(util.md5(bo.getPassword()))
                .setEmail(bo.getEmail())
                .setPhone(bo.getPhone())
                .setExpiration(DateUtil.addMinutesToDate(new Date(), 1));
    }

    private String getValidationUrl(String uuid){
        return "http://localhost:8080/api/user?token=" + uuid;
    }
}
