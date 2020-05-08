package com.grechur.main.net.impl;

import com.grechur.common.DefaultHttpService;
import com.grechur.main.net.IMainService;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserApi
 * @Description: 接口实现类
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:37
 */
public class MainApi {
    private static volatile MainApi mainApi;
    private IMainService mainService;

    private MainApi(){
        mainService = DefaultHttpService.createService(IMainService.class);
    }

    public static MainApi getUserApi() {
        if(mainApi == null){
            synchronized (MainApi.class){
                if(mainApi == null){
                    mainApi = new MainApi();
                }
            }
        }
        return mainApi;
    }


}
