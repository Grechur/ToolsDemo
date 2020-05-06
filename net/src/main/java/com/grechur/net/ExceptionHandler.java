package com.grechur.net;

import android.content.Intent;

import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


/**
 * Created by han on 2017/3/1.
 */

public class ExceptionHandler {
    public static ApiException handleException(Throwable e) {
        ApiException ex = null;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, 0);
            switch (httpException.code()) {
                case 404:
                    ex.setMessage("");
                    break;
                default:
                    ex.setMessage(e.getMessage());
                    break;
            }
            ex.setCode(httpException.code());
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, 1);
            ex.setMessage("连接超时，请稍后再试");

        } else if (e instanceof JsonParseException) {
            ex = new ApiException(e, 2);
            ex.setMessage("数据解析失败，请稍后再试");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            ex = new ApiException(e, 3);
            ex.setMessage("连接失败，请检查您的网络连接");
        } else if (e instanceof ApiException) {
            ex = (ApiException) e;
            if (((ApiException) e).getCode() == 10001) {
                try {
//                    Intent intent = new Intent("tokenAbate");
//                    LocalBroadcastManager.getInstance(HttpService.getContext()).sendBroadcast(intent);
                } catch (Exception e1) {
                }
            }

        } else {
            ex = new ApiException(e, -1);
            ex.setMessage(e.getMessage());
        }

//        if (BuildConfig.DEBUG)
//            ex.printStackTrace();
        return ex;
    }
}
