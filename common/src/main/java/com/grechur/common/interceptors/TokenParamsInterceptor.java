package com.grechur.common.interceptors;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by han on 2017/3/3.
 */

public class TokenParamsInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest=chain.request();
        String token="";
        if(!TextUtils.isEmpty(token)) {
            HttpUrl modifiedUrl = originRequest.url().newBuilder()
                    .addQueryParameter("token", "")
                    .build();
            Request newRequest = originRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(originRequest);
    }
}
