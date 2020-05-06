package com.grechur.toolsdemo.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.grechur.common.DefaultHttpService;
import com.grechur.common.util.DevicesUtils;
import com.grechur.common.util.SSLSocketFactoryHelper;
import com.grechur.toolsdemo.BuildConfig;
import com.simple.spiderman.SpiderMan;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: SelfApplication
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/4/24 16:46
 */
public class SelfApplication extends Application {
    private static Context context;

    public static Context getCurrent() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //放在其他库初始化前
        SpiderMan.init(this);
        //SSLSocketFactoryHelper.getSSLSocketFactory(this, SSLSocketFactoryHelper.CER_NAME), SSLSocketFactoryHelper.getHostVeritifer(),
        DefaultHttpService.initializeHttpService(this, AppConfig.baseUrl + "/", assembleUserAgent(), getVersionName(), "", "",
                getVersionCode(), "",  DevicesUtils.getSupportedAbis());
    }

    private int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    private String assembleUserAgent() {
        StringBuilder sb = new StringBuilder();
        sb.append("os/").append("Android").append(" ");
        sb.append("model/").append(Build.MODEL).append(" ");
        sb.append("brand/").append(Build.BRAND).append(" ");
        sb.append("sdk/").append(Build.VERSION.SDK_INT).append(" ");
//        sb.append("applicationID/").append(BuildConfig.APPLICATION_ID).append(" ");
        return sb.toString();
    }

    private String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

}
