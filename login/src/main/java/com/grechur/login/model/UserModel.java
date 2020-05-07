package com.grechur.login.model;


import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;

import com.grechur.login.bean.UserInfo;
import com.grechur.login.listener.BaseNetListener;
import com.grechur.login.net.impl.UserApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:59
 */
public class UserModel implements IUserModel{
//    MutableLiveData<UserInfo> userInfoLive = new MutableLiveData<>();
    @Override
    public void login(String name, String pwd, final BaseNetListener listener) {
        UserApi.getUserApi().login(name, pwd)
            .subscribe(new BaseSubscriber<UserInfo>() {
                @Override
                public void onNext(UserInfo userInfo) {
                    if(userInfo!=null){
//                        userInfoLive.setValue(userInfo);
                        if(listener!=null) listener.onSuccess(userInfo);
                    }
                }

                @Override
                public void onError(ApiException e) {
                    if(listener!=null) listener.onFail(e);
                }
            });
    }
}
