package com.grechur.login.model;


import androidx.lifecycle.MutableLiveData;

import com.grechur.login.bean.UserInfo;
import com.grechur.login.net.impl.UserApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.Map;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:59
 */
public class UserModel implements IUserModel{
    public MutableLiveData<UserInfo> userInfoLive = new MutableLiveData<>();
    public MutableLiveData<ApiException> error = new MutableLiveData<>();
    @Override
    public void login(String name, String pwd) {
        UserApi.getUserApi().login(name, pwd)
            .subscribe(new BaseSubscriber<UserInfo>() {
                @Override
                public void onNext(UserInfo userInfo) {
                    if(userInfo!=null){
                        userInfoLive.setValue(userInfo);
                    }
                }

                @Override
                public void onError(ApiException e) {
                    if(e!=null){
                        error.setValue(e);
                    }
                }
            });
    }

    @Override
    public void register(Map<String, Object> map) {
        UserApi.getUserApi().register(map).subscribe(new BaseSubscriber<UserInfo>() {
            @Override
            public void onNext(UserInfo userInfo) {
                if(userInfo!=null){
                    userInfoLive.setValue(userInfo);
                }
            }

            @Override
            public void onError(ApiException e) {
                if(e!=null){
                    error.setValue(e);
                }
            }
        });
    }
}
