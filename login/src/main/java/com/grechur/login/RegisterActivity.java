package com.grechur.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.SpUtils;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.login.bean.UserInfo;
import com.grechur.login.databinding.LoginActivityRegisterBinding;
import com.grechur.login.viewmodel.RegisterViewModel;
import com.grechur.net.ApiException;

@Route(path = RouterSchame.REGISTER_ACTIVITY)
public class RegisterActivity extends BaseActivity<RegisterViewModel, LoginActivityRegisterBinding> {


    @Override
    protected void initView() {
        viewModel.userModel.userInfoLive.observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                if(userInfo!=null){
                    ToastUtils.show("注册成功");
                    SpUtils.saveString(RegisterActivity.this,"registerName",viewModel.name.getValue());
                    SpUtils.saveString(RegisterActivity.this,"registerPwd",viewModel.name.getValue());
                    finish();
                }
            }
        });
        viewModel.userModel.error.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_register;
    }
}
