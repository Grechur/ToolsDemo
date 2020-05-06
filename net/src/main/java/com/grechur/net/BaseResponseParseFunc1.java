package com.grechur.net;

import com.google.gson.JsonParseException;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;


/**
 * Created by han on 2017/3/2.
 */

public class BaseResponseParseFunc1<T extends BaseResponse<R>,R> implements Function<T,R> {
    @Override
    public R apply(T t) throws Exception {
        if(t==null||t.heads==null){
            throw new JsonParseException("json error");
        }
        if(!t.isSuccess()){
            try{
                throw new ApiException(t.heads.code,t.heads.message);
            } catch (ApiException e) {
                throw Exceptions.propagate(e);
            }
        }
        return t.body;
    }

}
