package com.example.abstractionizer.login.jwt2.responses;

public class SuccessResponse<T> {
    private T data;

    public SuccessResponse(){
        this.data = (T)"SUCCESS";
    }

    public SuccessResponse(T data){
        this.data = data;
    }

    public SuccessResponse<T> setData(T data){
        this.data = data;
        return this;
    }

    public T getData(){
        return this.data;
    }
}
