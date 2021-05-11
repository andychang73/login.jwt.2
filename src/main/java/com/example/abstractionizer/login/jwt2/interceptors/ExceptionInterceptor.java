package com.example.abstractionizer.login.jwt2.interceptors;

import com.example.abstractionizer.login.jwt2.exceptions.CustomException;
import com.example.abstractionizer.login.jwt2.models.vo.CustomExceptionVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleAllExceptions(CustomException e){
        CustomExceptionVo response = new CustomExceptionVo(e.getCode(), e.getMsg(), e.getDetails());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }
}
