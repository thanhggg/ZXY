package com.gggstudio.chuctet.smstet.system;

import android.app.Application;

import com.gggstudio.chuctet.smstet.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


/**
 * Created by ThanhNT6793 on 7/16/2018
 */

public class ZXYApplication extends Application {

    private InterstitialAd interstitialAd;
    private RewardedVideoAd rewardedVideoAd;
    private static ZXYApplication instance;
    private OnAdLoadListener mListener;

    public interface OnAdLoadListener {
        void onAdLoaded();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                if (mListener != null) {
                    mListener.onAdLoaded();
                }
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                rewardedVideoAd.loadAd(getString(R.string.admob_app_ad_id),
                        new AdRequest.Builder().build());
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
        rewardedVideoAd.loadAd(getString(R.string.admob_app_ad_id),
                new AdRequest.Builder().build());

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admob_app_ad_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public static ZXYApplication getInstance() {
        return instance;
    }

    public RewardedVideoAd getRewardedVideoAd() {
        return rewardedVideoAd;
    }

    public InterstitialAd getInterstitialAd() {
        return interstitialAd;
    }

    public void setListener(OnAdLoadListener listener) {
        mListener = listener;
    }
}
