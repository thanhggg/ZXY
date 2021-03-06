package com.gggstudio.chuctet.smstet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.gggstudio.chuctet.smstet.adapter.PopularSMSAdapter;
import com.gggstudio.chuctet.smstet.db.ReadDB;
import com.gggstudio.chuctet.smstet.function.UtilFuntion;
import com.gggstudio.chuctet.smstet.lib.fadingactionbar.FadingActionBarHelper;
import com.gggstudio.chuctet.smstet.system.ZXYApplication;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;

public class GreetingsActivity extends ActivityBase {

    private int id_category = 0;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private AdView mAdView;
    private FadingActionBarHelper helper;
    private ListView listGreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new FadingActionBarHelper()
                .rootViewBackground(UtilFuntion.getRandomBackground())
                .actionBarBackground(R.drawable.actionbar_background)
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.activity_greetings);
        setContentView(helper.createView(this));
        helper.initActionBar(this);

        if (getActionBar() != null) {
            getActionBar().setIcon(R.drawable.ic_arrow_left);
        }
        getActionBar().setHomeButtonEnabled(true);
        ImageView iconImage = findViewById(android.R.id.home);
        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final int abTitleId = getResources().getIdentifier("action_bar_title", "id", "android");
        findViewById(abTitleId).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Do something
                onBackPressed();
            }
        });

        mAdView = findViewById(R.id.adView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_category = extras.getInt("id_category");
        }
        String mStringImg = null;
        if (extras != null) {
            mStringImg = extras.getString("mStringImg");
        }

        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>L�?i chúc"));
        ReadDB mReadDB = new ReadDB(GreetingsActivity.this);
        try {
            mReadDB.createDatabase();
            mReadDB.openDatabase();
        } catch (Exception ignored) {

        }
        ArrayList<Object> mLisSms = new ArrayList<Object>(mReadDB.getlistSMSObject(id_category));

        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + mReadDB.getTitleCates(id_category) + ""));
        mReadDB.close();

        ImageView image_header = findViewById(R.id.image_header);
        int imgDrawerble = getResources().getIdentifier(mStringImg, "drawable", getPackageName());
        image_header.setImageResource(imgDrawerble);

        listGreet = findViewById(android.R.id.list);
        PopularSMSAdapter adapter = new PopularSMSAdapter(GreetingsActivity.this, R.layout.item_list_sms, mLisSms);


        adapter.setListener(new PopularSMSAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos, final String msg) {
                AlertDialog actionDialog = new AlertDialog.Builder(GreetingsActivity.this)
                        .setTitle("Lựa chọn hành động")
                        .setPositiveButton("Gửi SMS", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createSendSmsIntent(msg);
                            }
                        }).setNegativeButton("Share Facebook", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createShareIntent(msg);
                            }
                        }).create();
                actionDialog.show();
            }
        });

        listGreet.setAdapter(adapter);

        setupFbShare();
    }

    private void setupFbShare() {
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(GreetingsActivity.this, "Có lỗi trong quá trình share, xin vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                Log.d("share facebook", error.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                mAdView.setVisibility(View.GONE);
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                mAdView.loadAd(adRequest);
            }
        });
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    void createShareIntent(String sms) {

        ShareHashtag shareHashtag = new ShareHashtag.Builder().setHashtag("hpny2019").build();
        ShareContent shareContent = new ShareLinkContent.Builder()
                .setShareHashtag(shareHashtag)
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.gggstudio.chuctet.smstet"))
                .setQuote(sms)
                .build();
        shareDialog.show(shareContent);
    }

    void createSendSmsIntent(String sms) {

        Uri uri = Uri.parse("smsto:");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", sms);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
