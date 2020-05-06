package com.grechur.common.interceptors;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by han on 2017/6/16.
 */

public class RefreshMsgInterceptor implements Interceptor{
    private Context mContext;
    public RefreshMsgInterceptor(Context context){
        mContext = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        String refreshMsgHeader = response.header("Refresh-Msg");
        if (null != refreshMsgHeader && "true".equals(refreshMsgHeader)) {
            Intent intent = new Intent("guidBroadcast");
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
        return response;
    }
}
