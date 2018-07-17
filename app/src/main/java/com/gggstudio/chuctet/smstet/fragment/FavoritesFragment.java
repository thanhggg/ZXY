package com.gggstudio.chuctet.smstet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gggstudio.chuctet.smstet.R;
import com.gggstudio.chuctet.smstet.adapter.FavoriteAdapter;
import com.gggstudio.chuctet.smstet.db.ReadDB;
import com.gggstudio.chuctet.smstet.db.SMSObject;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
	public static ListView lvFavorite;
	private Context mContext;
	private ReadDB mReadDB;
	private ArrayList<SMSObject> lstSmsObjectsFavorite;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_favotite, container, false);
		
		mContext = rootView.getContext();
		lvFavorite = rootView.findViewById(R.id.lvFavorite);
		
		lstSmsObjectsFavorite = new ArrayList<>();
		
		mReadDB = new ReadDB(mContext);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}
		
		lstSmsObjectsFavorite = mReadDB.getlistSMSObjectFavorite();
		
		FavoriteAdapter PopularSMSAdapter = new FavoriteAdapter(mContext,
				R.layout.item_list_sms_favorite, lstSmsObjectsFavorite);
		
		lvFavorite.setAdapter(PopularSMSAdapter);
		
		return rootView;
	}

}
