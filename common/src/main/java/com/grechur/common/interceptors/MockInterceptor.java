package com.grechur.common.interceptors;

import android.app.Application;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by han on 2017/3/17.
 */

public class MockInterceptor implements Interceptor {
    private Application mApplication;
    public MockInterceptor(Application application){
        mApplication = application;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Uri uri=Uri.parse(chain.request().url().toString());
        String queryMethod=uri.getQueryParameter("method");
        InputStream configIs=mApplication.getAssets().open("mock_configs.properties");
        Properties properties=new Properties();
        properties.load(configIs);
        if(!"true".equals(properties.getProperty(queryMethod))){
            return chain.proceed(chain.request());
        }
        String fileName=queryMethod.replaceAll("\\.","_");
        InputStream is=null;
        String responseString=null;
        try {
            is = mApplication.getAssets().open(fileName.toLowerCase() + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            responseString = new String(buffer);
        }catch (FileNotFoundException e){

        }finally {
            if(is!=null){
                is.close();
            }
        }
        if(responseString==null){
            return chain.proceed(chain.request());
        }
        return new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .addHeader("content-type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),responseString ))
                .build();
    }
}
