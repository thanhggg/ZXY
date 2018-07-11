package com.example.hiep.test1.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.hiep.test1.R;
import com.example.hiep.test1.SMSsActivity;
import com.example.hiep.test1.adapter.PopularSMSAdapter;
import com.example.hiep.test1.db.ReadDB;
import com.example.hiep.test1.db.SMSObject;

import java.util.ArrayList;


public class BetterFragment extends Fragment {
	public static ListView lvPopulated;
	private Context mContext;
	private ReadDB mReadDB;
	private ArrayList<SMSObject> lstSmsObjects;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_better, container, false);
		
		mContext = rootView.getContext();
		lvPopulated = (ListView) rootView.findViewById(R.id.lvPopulated);
		
		lstSmsObjects = new ArrayList<SMSObject>();
		
		mReadDB = new ReadDB(mContext);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		
		lstSmsObjects = mReadDB.getlistSMSObjectPopulate();
		
		PopularSMSAdapter PopularSMSAdapter = new PopularSMSAdapter(mContext,
				R.layout.item_list_sms, lstSmsObjects);
		
		lvPopulated.setAdapter(PopularSMSAdapter);
		
		lvPopulated.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				Intent mIntent = new Intent(mContext, SMSsActivity.class);
				mIntent.putExtra("sms_id",position);
				mIntent.putExtra("key",2);
				startActivity(mIntent);
			}
		});
		
		return rootView;
	}

}
