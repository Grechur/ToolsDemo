package com.grechur.net;

import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huangkangke on 2017/8/10.
 */

public class UAInterceptor implements Interceptor{
    public UAInterceptor(){

    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest=chain.request();
        Request requestWithUserAgent=originalRequest.newBuilder().header("User-Agent",assembleUserAgent())
                .addHeader("appVersion","3.3.0")
                .addHeader("appChannel","101").build();
        return chain.proceed(requestWithUserAgent);
    }

//        private String getVersionName() {
//        return BuildConfig.VERSION_NAME;
//    }
//
//    private String getChannel() {
//        return AppConfig.CHANNEL_ID;
//    }
//
    private String assembleUserAgent(){
        StringBuilder sb = new StringBuilder();
        sb.append("os/").append("Android").append(" ");
        sb.append("model/").append(Build.MODEL).append(" ");
        sb.append("brand/").append(Build.BRAND).append(" ");
        sb.append("sdk/").append(Build.VERSION.SDK_INT).append(" ");
//        sb.append("applicationID/").append(BuildConfig.APPLICATION_ID).append(" ");
        return sb.toString();
    }
}
