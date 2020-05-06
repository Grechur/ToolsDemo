package com.grechur.net;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by han on 2017/3/1.
 */

public class BSDServiceFactory {
    /**
     * 测试环境 https://sh.xiaoxiangyoupin.cn/portal/
     * 生产环境 https://api.xiaoxiangyoupin.cn/portal/
     */
   //  public static String BASE_URL="http://dev.xiaoxiangyoupin.com:1111/v2/";//测试环境
   //  public static String BASE_URL="http://dev.xiaoxiangyoupin.com:1112/v2/";//仿真环境
    public static String BASE_URL="http://api.xiaoxiangyoupin.com/v2/";//正式环境

    private static volatile BSDServiceFactory sServiceFactory = null;

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    private BSDServiceFactory() {
        mOkHttpClient = getClient();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static BSDServiceFactory getInstance() {
        if (sServiceFactory == null) {
            synchronized (BSDServiceFactory.class) {
                if (sServiceFactory == null) {
                    sServiceFactory = new BSDServiceFactory();
                }
            }
        }
        return sServiceFactory;
    }

    public <T> T createCustomService(Class<T> serviceClazz, long timeout) {
        OkHttpClient client = mOkHttpClient.newBuilder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClazz);
    }


    public <T> T createService(Class<T> serviceClazz) {
        return mRetrofit.create(serviceClazz);
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        X509TrustManager trustManager = getX509TrustManager();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logging);
//            builder.addInterceptor(new MockInterceptor());
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        //TODO 使用ServiceFactory的类需要改变用法，无法调用userAgent的Interceptor
        builder.addInterceptor(new UAInterceptor());
        return builder
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(getSSLSocketFactory(trustManager), trustManager)
                .build();
    }

    private X509TrustManager getX509TrustManager() {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init((KeyStore) null);
            TrustManager[] trustManagers = factory.getTrustManagers();
            return (X509TrustManager) trustManagers[0];
        } catch (NoSuchAlgorithmException | KeyStoreException exception) {
//            Log.e(getClass().getSimpleName(), "not trust manager available", exception);
        }

        return null;
    }


    private SSLSocketFactory getSSLSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException exception) {
//            Log.e(getClass().getSimpleName(), "not tls ssl socket factory available", exception);
        }

        return (SSLSocketFactory) SSLSocketFactory.getDefault();
    }
}
