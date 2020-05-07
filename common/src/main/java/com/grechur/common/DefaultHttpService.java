package com.grechur.common;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.stetho.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.grechur.common.interceptors.RefreshMsgInterceptor;
import com.grechur.common.interceptors.UserAgentInterceptor;
import com.grechur.net.HttpService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by han on 2017/7/27.
 */

public class DefaultHttpService {
    private final static String BASE_URL = "https://www.wanandroid.com";//正式环境
    private static HttpService sHttpService;
    private static HttpService.Builder builder;
    public static synchronized void initializeHttpService(Context context, String baseUrl, String assembleUserAgent, String versionName, String channalId, String deviceId, int versonCode,String imei2, String supportedAbis) {
        initializeHttpService(context,baseUrl,assembleUserAgent,versionName,channalId,deviceId,versonCode,imei2,null,null,supportedAbis);
    }
    /**
     * 初始化网络模块
     * @param context
     * @param baseUrl
     * @param imei2
     * @param hostVeritifer
     */
    public static synchronized void initializeHttpService(Context context, String baseUrl, String assembleUserAgent, String versionName, String channalId, String deviceId, int versonCode,String imei2, SSLSocketFactory factory, HostnameVerifier hostVeritifer, String supportedAbis) {
        builder = new HttpService.Builder();
        builder.baseUrl(TextUtils.isEmpty(baseUrl) ? BASE_URL : baseUrl);
        builder.addInterceptor(new RefreshMsgInterceptor(context == null ? Applications.getCurrent() : context));
        sHttpService.setContext(context == null ? Applications.getCurrent() : context);
        builder.addInterceptor(new UserAgentInterceptor(assembleUserAgent, versionName, channalId,deviceId,versonCode,imei2, supportedAbis));
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        if (BuildConfig.DEBUG) {
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
//        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logging);
            builder.addNetworkInterceptor(new StethoInterceptor());
//        }
        builder.sslSocketFactory(factory);
        builder.hostVeritifer(hostVeritifer);
    }

    public static synchronized void addInterceptor(Interceptor interceptor) {
        builder.addInterceptor(interceptor);
    }

    public static synchronized <T> T createService(Class<T> serviceClazz) {
        if (builder == null) {
            throw new RuntimeException("initializeHttpService first");
        }
        if (sHttpService == null) {
            sHttpService = builder.build();
        }
        return sHttpService.createRetrofitService(serviceClazz);
    }

/*    public static synchronized <T> T createService(Class<T> serviceClazz,long time) {
        if (builder == null) {
            throw new RuntimeException("initializeHttpService first");
        }
        if (sHttpService == null) {
            sHttpService = builder.build();
        }
        HttpService.Builder tmpBuilder = sHttpService.newBuilder();
        tmpBuilder.connectTimeout(time, TimeUnit.SECONDS);
        return tmpBuilder.build().createRetrofitService(serviceClazz);
    }*/

    public static HttpService getHttpService(){
        if (builder == null) {
            throw new RuntimeException("initializeHttpService first");
        }
        if (sHttpService == null) {
            sHttpService = builder.build();
        }
        return sHttpService;
    }

}
