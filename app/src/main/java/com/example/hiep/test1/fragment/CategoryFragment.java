
package com.example.hiep.test1.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.hiep.test1.R;
import com.example.hiep.test1.adapter.AdapterCategorys;
import com.example.hiep.test1.db.CategoryObject;
import com.example.hiep.test1.db.ReadDB;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
	public static ListView lvCategory;
	private ReadDB mReadDB;
	private Context mContext;
	ArrayList<CategoryObject> mListTheLoai;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_category, container, false);
		mContext=rootView.getContext();
		lvCategory = (ListView) rootView.findViewById(R.id.lvCategory);

		mReadDB = new ReadDB(mContext);
		try {
			mReadDB.createDatabase();
			mReadDB.openDatabase();
		} catch (Exception e) {

		}

		mListTheLoai = new ArrayList<CategoryObject>();
		mListTheLoai = mReadDB.getListTheloai();

		AdapterCategorys mAdapterCategorys = new AdapterCategorys(mContext,
				R.layout.list_categorys_layout, mListTheLoai);

		lvCategory.setAdapter(mAdapterCategorys);

		lvCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
			}
		});

		mReadDB.closeDatabase();
		return rootView;
	}
}
