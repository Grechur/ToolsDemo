package com.grechur.common.util.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grechur.common.R;


/**
 * 自定义Toast工具类
 * 创建：WithWings 时间 2019/6/15
 * Email:wangtong1175@sina.com
 */
public class ToastMessage {

    public static Toast getBlackToast(Context context, String message) {
        //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
        LayoutInflater inflater = LayoutInflater.from(context);//调用Activity的getLayoutInflater()
        View view = inflater.inflate(R.layout.common_toast_black, null); //加載layout下的布局
        TextView tvToastMessage = (TextView) view.findViewById(R.id.tv_toast_message);
        tvToastMessage.setText(message); //toast内容
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, dip2px(context, 65));//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setView(view); //添加视图文件
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        return toast;
    }


    private static int dip2px(Context context, float dpValue) {
        if (context == null) {
            return 0;
        } else {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int)(dpValue * scale + 0.5F);
        }
    }

}
