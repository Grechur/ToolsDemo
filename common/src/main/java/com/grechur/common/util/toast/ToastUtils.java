package com.grechur.common.util.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.grechur.common.Applications;


public class ToastUtils {
    private static Context mContext = Applications.getCurrent();
    private static YPToast mToast;

    public static void show(@StringRes int resId) {
        if (null == mContext)
            return;
        show(mContext.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int resId, int duration) {
        if (null == mContext)
            return;
        show(mContext.getResources().getText(resId), duration);
    }

    public static void show(@NonNull CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, @NonNull CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(@NonNull final CharSequence text, final int duration) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    show(text, duration);
                }
            });
        } else {
            if (mToast == null) {
                if (null == mContext)
                    return;
                mToast = YPToast.makeText(mContext, text, duration);
            } else {
                mToast.cancel();
                mToast = YPToast.makeText(mContext, text, duration);
            }
            mToast.show();
        }
    }

    public static void show(@StringRes int resId, Object... args) {
        if (null == mContext)
            return;
        show(String.format(mContext.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(@NonNull String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int resId, int duration, Object... args) {
        if (null == mContext)
            return;
        show(String.format(mContext.getResources().getString(resId), args),
                duration);
    }

    public static void show(@NonNull String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }

    public static void showApiException(Throwable exp) {
        String msg = null;
        if (null != exp)
            msg = exp.getMessage();
        if (TextUtils.isEmpty(msg))
            msg = "服务器开小差了, 请稍后重试";
        show(msg);
    }

    public static void cancel() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    cancel();
                }
            });
        } else {
            if (null != mToast)
                mToast.cancel();
        }
    }

    public static void makeText(@NonNull Context context, @NonNull CharSequence text, int duration) {
        show(text, duration);
    }

    public static void makeText(@NonNull Context context, @StringRes int textRes, int duration) {
        show(context.getString(textRes), duration);
    }
}
