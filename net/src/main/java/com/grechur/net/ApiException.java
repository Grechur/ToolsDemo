package com.grechur.net;

/**
 * Created by han on 2017/3/1.
 */

public class ApiException extends RuntimeException{
    private int mCode;
    private String mMessage;
    public ApiException(Throwable throwable,int code){
        super(throwable);
        mCode=code;
    }
    public ApiException(Throwable throwable,int code,String message){
        super(throwable);
        mCode=code;
        mMessage=message;
    }

    public ApiException(int code,String message){
        super(message);
        mCode=code;
        mMessage=message;
    }

    public int getCode(){
        return mCode;
    }

    public String getMessage(){
        return mMessage;
    }

    public void setCode(int code){
        this.mCode=code;
    }

    public void setMessage(String message){
        this.mMessage=message;
    }
}
