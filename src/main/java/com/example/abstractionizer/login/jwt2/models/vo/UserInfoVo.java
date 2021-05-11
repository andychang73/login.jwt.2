package com.example.abstractionizer.login.jwt2.models.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoVo {
    private Integer userId;
    private String username;
    private String email;
    private String phone;
}
