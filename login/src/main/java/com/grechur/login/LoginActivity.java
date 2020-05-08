package com.grechur.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.login.bean.UserInfo;
import com.grechur.login.databinding.LoginActivityLoginBinding;
import com.grechur.login.viewmodel.LoginViewModel;
import com.grechur.net.ApiException;

@Route(path = RouterSchame.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginViewModel, LoginActivityLoginBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView() {

        viewModel.pwdVis.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    binding.loginPas.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    binding.loginPas.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        viewModel.userModel.userInfoLive.observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                if(userInfo!=null){
                    ToastUtils.show("登录成功");
                    finish();
                }
            }
        });
        viewModel.userModel.error.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null){
                    ToastUtils.show(e.getMessage());
                }
            }
        });
    }

}
