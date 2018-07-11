package com.example.hiep.test1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.hiep.test1.adapter.SMSViewpagerAdapter;
import com.example.hiep.test1.db.ReadDB;
import com.example.hiep.test1.db.SMSObject;

import java.util.ArrayList;


public class SMSsActivity extends ActivityBase {
	private ReadDB mReadDB;
	private ViewPager mPager;
	private SMSViewpagerAdapter mPagerAdapter;
	private ArrayList<SMSObject> lstSMS = new ArrayList<SMSObject>();
	private TextView tvBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sms_view_activity);
		getActionBar().hide();

		Intent mIntent = getIntent();
		int position = 0;
		int key = 0;
		int id_category = 0;
		mPager = (ViewPager) findViewById(R.id.view_pager);
		tvBack = (TextView) findViewById(R.id.tvBack);

		if (mIntent != null) {
			position = Integer.valueOf(mIntent.getIntExtra("sms_id",0));
			id_category = Integer
					.valueOf(mIntent.getIntExtra("id_category",0));
			key = Integer.valueOf(mIntent.getIntExtra("key",0));
		}

		tvBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		getData(position, id_category, key);

	}

	public void getData(int current, int id_category,int key) {
		mReadDB = new ReadDB(SMSsActivity.this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		if (lstSMS != null)
			lstSMS.clear();

		if (key == 2) {
			lstSMS = mReadDB.getlistSMSObjectPopulate();
		} else if(key==1) {
			lstSMS = mReadDB.getlistSMSObject(id_category);
		}
		else{
		}

		mPagerAdapter = new SMSViewpagerAdapter(SMSsActivity.this, lstSMS);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(current);

		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		mReadDB.closeDatabase();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
}
