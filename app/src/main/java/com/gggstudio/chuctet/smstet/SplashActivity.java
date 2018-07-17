package com.gggstudio.chuctet.smstet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getActionBar() != null) {
            getActionBar().hide();
        }

        logo = findViewById(R.id.logo_img);

        if (savedInstanceState == null) {
            flyIn();
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                endSplash();
            }
        }, 3000);
    }

    private void flyIn() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        logo.startAnimation(animation);
    }

    private void endSplash() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

}
