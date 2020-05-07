package com.grechur.common.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.BR;

import java.lang.reflect.ParameterizedType;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: BaseActivity
 * @Description: 基础activity
 * @Author: Grechur
 * @CreateDate: 2020/5/7 10:50
 */
public abstract class BaseActivity<VM extends BaseViewModel,VDB extends ViewDataBinding> extends AppCompatActivity {

    protected VDB binding;
    protected VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(getTClass());
        viewModel.finish.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                finish();
            }
        });
        binding = DataBindingUtil.setContentView(this,getLayoutId());
        ARouter.getInstance().inject(this);
        //所有布局中dababinding对象变量名称都是vm
        binding.setVariable(BR.vm,viewModel);
        binding.executePendingBindings();//立即更新UI
//        binding.setLifecycleOwner(this);
        getLifecycle().addObserver(viewModel);
        initView();
    }

    /**
     * 获取泛型对相应的Class对象
     * @return
     */
    private Class<VM>  getTClass(){
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        //返回表示此类型实际类型参数的 Type 对象的数组()，想要获取第二个泛型的Class，所以索引写1
        return (Class)type.getActualTypeArguments()[0];//<T>
    }

    protected abstract void initView();

    protected abstract int getLayoutId();
}
