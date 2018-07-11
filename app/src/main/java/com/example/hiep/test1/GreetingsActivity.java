package com.example.hiep.test1;

import java.util.ArrayList;

import android.content.Intent;
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
import com.example.hiep.test1.lib.fadingactionbar.FadingActionBarHelper;

public class GreetingsActivity extends ActivityBase {

	private ListView listGreet;
	private int id_category = 0;
	private String mStringImg="";

	private ReadDB mReadDB;
	private ArrayList<SMSObject> mLisSms;
	private ImageView image_header;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_greetings);

		FadingActionBarHelper helper = new FadingActionBarHelper()
        .actionBarBackground(R.drawable.bg_item_red_actionbar)
        .headerLayout(R.layout.header)
        .contentLayout(R.layout.activity_greetings);
        setContentView(helper.createView(this));
        helper.initActionBar(this);
        
        getActionBar().setIcon(R.drawable.ic_arrow_left);
        getActionBar().setHomeButtonEnabled(true);
        ImageView iconImage = (ImageView) findViewById(android.R.id.home);
        iconImage.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	onBackPressed();
            }
        });
        
		
		Bundle extras = getIntent().getExtras();
		id_category = extras.getInt("id_category");
		mStringImg=extras.getString("mStringImg");
		
		getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>L�?i chúc"));
		mReadDB = new ReadDB(GreetingsActivity.this);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		mLisSms=new ArrayList<SMSObject>();
		mLisSms=mReadDB.getlistSMSObject(id_category);
		getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" +mReadDB.getTitleCates(id_category)+""));
		mReadDB.close();
		
		image_header=(ImageView)findViewById(R.id.image_header);
		int imgDrawerble = getResources().getIdentifier(mStringImg, "drawable", getPackageName());
		image_header.setImageResource(imgDrawerble);
		
		listGreet = (ListView) findViewById(android.R.id.list);
		PopularSMSAdapter adapter=new PopularSMSAdapter(GreetingsActivity.this, R.layout.item_list_sms, mLisSms);
		listGreet.setAdapter(adapter);
		
		listGreet.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(GreetingsActivity.this, SMSsActivity.class);
				mIntent.putExtra("sms_id",arg2);
				mIntent.putExtra("id_category",id_category);
				mIntent.putExtra("key", 1);
				startActivity(mIntent);
			}
		});
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
}

}
