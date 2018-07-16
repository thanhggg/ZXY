package com.example.hiep.test1.system;

import android.app.Application;

import com.example.hiep.test1.R;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by ThanhNT6793 on 7/16/2018
 */

public class ZXYApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MobileAds.initialize(this,getString(R.string.admob_app_id));
    }
}
