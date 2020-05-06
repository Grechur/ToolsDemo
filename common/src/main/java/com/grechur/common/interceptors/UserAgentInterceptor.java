package com.grechur.common.interceptors;

import android.text.TextUtils;

import com.grechur.common.Applications;
import com.grechur.common.util.NetworkUtils;
import com.grechur.common.util.SpUtils;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by han on 2017/4/14.
 */

public class UserAgentInterceptor implements Interceptor {
    String mAssembleUserAgent;
    String mVersionName;
    String mChannalId;
    String mDeviceId;
    int versionCode;
    String imei2;
    String supportedAbis;
    String oaid;
    private final String apptype = "0";//0 小象优品 ，  4 新浪分期
    public UserAgentInterceptor(String assembleUserAgent, String versionName, String channalId,
                                String deviceId, int versonCode, String imei2, String supportedAbis) {
        this.mAssembleUserAgent = assembleUserAgent;
        this.mVersionName = versionName;
        this.mChannalId = channalId;
        this.mDeviceId = deviceId;
        this.versionCode = versonCode;
        this.imei2 = imei2;
        this.supportedAbis = supportedAbis;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (TextUtils.isEmpty(mDeviceId)) {
            mDeviceId = SpUtils.getString(Applications.getCurrent(), "imei", "");
        }
        Request originalRequest = chain.request();
        Request.Builder header = originalRequest.newBuilder().header("User-Agent", mAssembleUserAgent)
                .addHeader("appVersion", mVersionName)
                .addHeader("Build", String.valueOf(versionCode))
                .addHeader("appChannel", mChannalId)
                .addHeader("DeviceId", mDeviceId)
                .addHeader("Lon", String.valueOf(SpUtils.getFloat(Applications.getCurrent(), "lon", 0)))
                .addHeader("Lat", String.valueOf(SpUtils.getFloat(Applications.getCurrent(), "lat", 0)))
                .addHeader("province", URLEncoder.encode(SpUtils.getString(Applications.getCurrent(), "province", ""), "UTF-8"))
                .addHeader("city", URLEncoder.encode(SpUtils.getString(Applications.getCurrent(), "city", ""), "UTF-8"))
                .addHeader("district", URLEncoder.encode(SpUtils.getString(Applications.getCurrent(), "district", ""), "UTF-8"))
                .addHeader("street", URLEncoder.encode(SpUtils.getString(Applications.getCurrent(), "street", ""), "UTF-8"))
                .addHeader("address", URLEncoder.encode(SpUtils.getString(Applications.getCurrent(), "address", ""), "UTF-8"))
                .addHeader("netWork", URLEncoder.encode(NetworkUtils.getNetTypeName(), "UTF-8"))
                .addHeader("apptype", apptype)
                .addHeader("supportedAbis", TextUtils.isEmpty(supportedAbis) ? "unknown" : supportedAbis);
        String token = SpUtils.getToken(Applications.getCurrent());

        if (!TextUtils.isEmpty(token)) {
            header.addHeader("token", token);
        }
        if (TextUtils.isEmpty(imei2)) {
            imei2 = SpUtils.getString(Applications.getCurrent(), "imei2", "");
        }
        if (!TextUtils.isEmpty(imei2)) {
            header.addHeader("DeviceId2", imei2);
        }

        if (!TextUtils.isEmpty(oaid)) {
            header.addHeader("oaid", oaid);
        } else {
            String templOaid = SpUtils.getString(Applications.getCurrent(), "key_device_oaid", "");
            if (!TextUtils.isEmpty(templOaid)) {
                oaid =  templOaid;
                header.addHeader("oaid", oaid);
            }
        }
        Request requestWithUserAgent = header.build();
        return chain.proceed(requestWithUserAgent);
    }


}
