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
import com.grechur.login.databinding.LoginActivityLoginBinding;
import com.grechur.login.viewmodel.LoginViewModel;

@Route(path = RouterSchame.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginViewModel, LoginActivityLoginBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

}
