package com.example.abstractionizer.login.jwt2.models.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterBo {

    @NotEmpty
    private String username;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*_+=])(?=\\S+$).{8,}$")
    private String password;

    @NotEmpty
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    @NotEmpty
    @Pattern(regexp = "09\\d{8}")
    private String phone;

}
