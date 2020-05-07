package com.grechur.login.viewmodel;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.grechur.common.Applications;
import com.grechur.common.base.BaseViewModel;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.SpUtils;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.login.bean.UserInfo;
import com.grechur.login.listener.BaseNetListener;
import com.grechur.login.model.UserModel;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: LoginViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/7 10:40
 */
public class LoginViewModel extends BaseViewModel {
    public ObservableField<Boolean> remPwd = new ObservableField<>();//记住密码
    public ObservableField<String> name = new ObservableField<>();//用户名
    public ObservableField<String> pwd = new ObservableField<>();//密码
    public MutableLiveData<Boolean> pwdVis = new MutableLiveData<>();//密码是否可见

    private UserModel userModel;

    public void pasVisibility(){
        pwdVis.setValue(pwdVis.getValue()==null?false:!pwdVis.getValue());
    }

    @Override
    protected void create() {
        super.create();
        boolean rem = SpUtils.getBoolean(Applications.getCurrent(),"remPas",false);
        remPwd.set(rem);
        if(rem){
            name.set(SpUtils.getString(Applications.getCurrent(),"name",""));
            pwd.set(SpUtils.getString(Applications.getCurrent(),"pwd",""));
        }
        userModel = new UserModel();
    }

    public void login() {
        String m = name.get();
        String p = pwd.get();
        if (TextUtils.isEmpty(m)) {
            ToastUtils.show("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(p)) {
            ToastUtils.show("请输入密码");
            return;
        }
        if (remPwd.get()) {
            SpUtils.saveString(Applications.getCurrent(),"mobile", m);
            SpUtils.saveString(Applications.getCurrent(),"pas", p);
        }
        userModel.login(m, p, new BaseNetListener<UserInfo>() {
            @Override
            public void onSuccess(UserInfo o) {
                if(o!=null){
                    ToastUtils.show("登录成功");
                    intentByRouter(RouterSchame.MAIN_ACTIVITY);
                    finish();
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                if(throwable!=null){
                    ToastUtils.show(throwable.getMessage());
                }
            }
        });
    }
}
