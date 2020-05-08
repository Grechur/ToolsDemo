package com.grechur.login.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.login.model.UserModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: RegisterViewModel
 * @Description: 注册的viewmodel
 * @Author: Grechur
 * @CreateDate: 2020/5/8 11:06
 */
public class RegisterViewModel extends BaseViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> pwd = new MutableLiveData<>();
    public MutableLiveData<String> rePwd = new MutableLiveData<>();

    public UserModel userModel;

    public RegisterViewModel() {
        userModel = new UserModel();
    }

    @Override
    protected void create() {
        super.create();

    }

    public void register(){
        String n  = name.getValue();
        String p = pwd.getValue();
        String rep = rePwd.getValue();
        if(TextUtils.isEmpty(n)){
            ToastUtils.show("请输入用户名");
        }

        if(TextUtils.isEmpty(p)){
            ToastUtils.show("请输入密码");
        }else{
            if(TextUtils.isEmpty(rep)){
                ToastUtils.show("请确认密码");
            }
        }

        if(!TextUtils.equals(p,rep)){
            ToastUtils.show("确认密码与密码不符");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("username",n);
        map.put("password",p);
        map.put("repassword",rep);
        userModel.register(map);
    }
}
