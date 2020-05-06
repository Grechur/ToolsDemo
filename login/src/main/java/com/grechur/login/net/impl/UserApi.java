package com.grechur.login.net.impl;

import com.grechur.common.DefaultHttpService;
import com.grechur.login.bean.UserInfo;
import com.grechur.login.net.IUserService;
import com.grechur.net.BaseTransformer;

import io.reactivex.Observable;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserApi
 * @Description: 接口实现类
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:37
 */
public class UserApi {
    private static volatile UserApi userApi;
    private IUserService userService;

    private UserApi(){
        userService = DefaultHttpService.createService(IUserService.class);
    }

    public static UserApi getUserApi() {
        if(userApi == null){
            synchronized (UserApi.class){
                if(userApi == null){
                    userApi = new UserApi();
                }
            }
        }
        return userApi;
    }

    /**
     * 登录接口
     * @param name
     * @param pwd
     * @return
     */
    public Observable<UserInfo> login(String name,String pwd){
        return userService.login(name, pwd).compose(BaseTransformer.<UserInfo>applyTransform());
    }
}
