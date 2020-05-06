package com.grechur.login.model;


import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;

import com.grechur.login.bean.UserInfo;
import com.grechur.login.net.impl.UserApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:59
 */
public class UserModel implements IUserModel{
    MutableLiveData<UserInfo> userInfo = new MutableLiveData<>();
    @Override
    public void login(String name, String pwd) {
        UserApi.getUserApi().login(name, pwd)
            .subscribe(new BaseSubscriber<UserInfo>() {
                @Override
                public void onNext(UserInfo userInfo) {
                    if(userInfo!=null){
                    }
                }

                @Override
                public void onError(ApiException e) {

                }
            });
    }
}
