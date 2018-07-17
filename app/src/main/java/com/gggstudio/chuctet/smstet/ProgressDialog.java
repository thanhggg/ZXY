package com.gggstudio.chuctet.smstet;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ThanhNT6793 on 7/17/2018
 */

public class ProgressDialog extends Dialog {
    private ImageView iv;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.TransparentDialog);
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams wlmp = window.getAttributes();
        wlmp.gravity = Gravity.CENTER;
        window.setAttributes(wlmp);
        window.requestFeature(Window.FEATURE_NO_TITLE);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        iv = new ImageView(context);
        iv.setImageResource(R.mipmap.ic_progress_loading);
        layout.addView(iv, params);
        addContentView(layout, params);
    }

    @Override
    public void show() {
        try {
            super.show();
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f,
                    Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF,
                    .5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(2000);
            iv.setAnimation(anim);
            iv.startAnimation(anim);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
