package com.example.abstractionizer.login.jwt2.login.controllers;

import com.example.abstractionizer.login.jwt2.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt2.models.bo.UserLoginBo;
import com.example.abstractionizer.login.jwt2.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt2.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt2.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserBusiness userBusiness;

    @PostMapping
    public SuccessResponse<String> register(@RequestBody @Valid UserRegisterBo bo){
        return new SuccessResponse<>(userBusiness.register(bo));
    }

    @GetMapping
    public SuccessResponse<UserInfoVo> validateAccount(@RequestParam("token") String token){
        return new SuccessResponse<>(userBusiness.accountValidation(token));
    }

    @PostMapping("/login")
    public SuccessResponse<String> login(@RequestBody @Valid UserLoginBo bo){
        return new SuccessResponse<>(userBusiness.login(bo));
    }

    @GetMapping("logOut")
    public SuccessResponse logOut(@RequestHeader("token") String token){
        userBusiness.logOut(token);
        return new SuccessResponse();
    }
}
