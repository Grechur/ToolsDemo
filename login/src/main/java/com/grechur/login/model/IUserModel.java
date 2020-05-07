package com.grechur.login.model;

import com.grechur.login.listener.BaseNetListener;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: IUserModel
 * @Description: model层
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:55
 */
public interface IUserModel {
    void login(String name, String pwd, BaseNetListener listener);
}
