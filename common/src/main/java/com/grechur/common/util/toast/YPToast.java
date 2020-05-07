package com.grechur.common.util.toast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.grechur.common.R;


public class YPToast extends Toast {

    private TextView mText;
    private Context mContext;

    private YPToast(@NonNull Context context) {
        super(context);
        mContext = context;
        initView();
    }


    public static YPToast makeText(@NonNull Context context, @NonNull CharSequence text, int duration) {
        YPToast ypToast = new YPToast(context);
        ypToast.setText(text);
        ypToast.setDuration(duration);
        return ypToast;
    }

    @Override
    public void setText(@NonNull CharSequence text) {
        if (null == mText)
            return;
        mText.setText(text);
    }

    @Override
    public void setText(@StringRes int resId) {
        setText(mContext.getText(resId));
    }


    private void initView() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int padding = ((int) displayMetrics.density * 10);
        mText = new TextView(mContext);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.common_toast_bg);
        drawable.setAlpha((int) (255 * 0.7));
        mText.setBackground(drawable);
        mText.setMinWidth((int) (displayMetrics.density * 200));
        mText.setMaxWidth((int) (displayMetrics.widthPixels - displayMetrics.density * 100));
        mText.setPadding(padding, padding, padding, padding);
        mText.setGravity(Gravity.CENTER);
        mText.setTextColor(mContext.getResources().getColor(android.R.color.white));
        setView(mText);
        setGravity(Gravity.CENTER, 0, 0);
    }
}
