package com.example.hiep.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hiep.test1.adapter.PopularSMSAdapter;
import com.example.hiep.test1.db.ReadDB;
import com.example.hiep.test1.db.SMSObject;
import com.example.hiep.test1.function.UtilFuntion;
import com.example.hiep.test1.lib.fadingactionbar.FadingActionBarHelper;

import java.util.ArrayList;

public class GreetingsActivity extends ActivityBase {

    private int id_category = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_greetings);

        FadingActionBarHelper helper = new FadingActionBarHelper()
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
        ArrayList<SMSObject> mLisSms;
        mLisSms = mReadDB.getlistSMSObject(id_category);
        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + mReadDB.getTitleCates(id_category) + ""));
        mReadDB.close();

        ImageView image_header = findViewById(R.id.image_header);
        int imgDrawerble = getResources().getIdentifier(mStringImg, "drawable", getPackageName());
        image_header.setImageResource(imgDrawerble);

        ListView listGreet = findViewById(android.R.id.list);
        PopularSMSAdapter adapter = new PopularSMSAdapter(GreetingsActivity.this, R.layout.item_list_sms, mLisSms);
        listGreet.setAdapter(adapter);

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

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    void createShareIntent(String sms) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sms);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "share"));
    }

    void createSendSmsIntent(String sms) {

        Uri uri = Uri.parse("smsto:");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", sms);
        startActivity(it);
    }

}
