package com.example.abstractionizer.login.jwt2.login.businesses;
import com.example.abstractionizer.login.jwt2.models.bo.UserLoginBo;
import com.example.abstractionizer.login.jwt2.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt2.models.vo.UserInfoVo;

public interface UserBusiness {

    String register(UserRegisterBo bo);

    UserInfoVo accountValidation(String uuid);

    String login(UserLoginBo bo);

    void logOut(String token);
}
