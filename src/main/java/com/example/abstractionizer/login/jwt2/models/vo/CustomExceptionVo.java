package com.example.abstractionizer.login.jwt2.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomExceptionVo {
    private String code;
    private String msg;
    private String details;
}
