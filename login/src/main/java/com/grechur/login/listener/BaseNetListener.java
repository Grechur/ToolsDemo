package com.grechur.login.listener;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: BaseNetListener
 * @Description: 回调
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:57
 */
public interface BaseNetListener<T> {

    void onSuccess(T t);

    void onFail(Throwable throwable);

}
