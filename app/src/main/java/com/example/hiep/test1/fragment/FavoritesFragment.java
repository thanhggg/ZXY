package com.example.hiep.test1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hiep.test1.R;
import com.example.hiep.test1.adapter.FavoriteAdapter;
import com.example.hiep.test1.db.ReadDB;
import com.example.hiep.test1.db.SMSObject;

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
		lvFavorite = (ListView) rootView.findViewById(R.id.lvFavorite);
		
		lstSmsObjectsFavorite = new ArrayList<SMSObject>();
		
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
