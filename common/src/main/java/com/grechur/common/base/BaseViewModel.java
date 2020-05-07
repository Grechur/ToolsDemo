package com.grechur.common.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: BaseViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/7 10:59
 */
public abstract class BaseViewModel extends ViewModel implements LifecycleObserver {
    public MutableLiveData<Void> finish = new MutableLiveData<>();
    /**
     * @param path 传送Activity的
     */
    public void intentByRouter(String path) {
        ARouter.getInstance().build(path)
                .navigation();
    }

    public void finish(){
        finish.setValue(null);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void create(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void start(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void resume(){ }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void pause(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void stop(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void destroy(){}
}
