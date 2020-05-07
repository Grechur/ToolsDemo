package com.grechur.toolsdemo.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.alibaba.android.arouter.launcher.ARouter;
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
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private boolean isDebug() {
        return BuildConfig.DEBUG;
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
