package com.gggstudio.chuctet.smstet.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gggstudio.chuctet.smstet.R;
import com.gggstudio.chuctet.smstet.db.CategoryObject;
import com.gggstudio.chuctet.smstet.db.SMSObject;

import java.util.ArrayList;


public class FavoriteAdapter extends BaseAdapter {
	private int resouce;
	Context context = null;
	 private LayoutInflater inflater;
	ArrayList<SMSObject> arr = null;
	
	public FavoriteAdapter(Context context, int resouce, ArrayList<SMSObject> list) {
		this.context = context;
		this.resouce = resouce;
		inflater = LayoutInflater.from( context );
		this.arr = list;
	}
	
	 @Override
	    public int getCount(){
	        return arr.size();
	    }
	    @Override
	    public CategoryObject getItem(int i) {
	        return null;
	    }
	    @Override
	    public long getItemId(int i) {
	        return 0;
	    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder = new ViewHolder();
		SMSObject sms = arr.get(position);
		
		if (convertView == null) {
			convertView = inflater.inflate(resouce, null);
			mHolder.tvSMS = convertView.findViewById(R.id.tvSMSNameFavorite);
			convertView.setTag(mHolder);
		}else{
			mHolder = (ViewHolder) convertView.getTag();
		}
		
		if(sms != null){
			mHolder.tvSMS.setTextColor(context.getResources().getColor(R.color.red));
			mHolder.tvSMS.setText(sms.getContent());
		}
		
		
		return convertView;
	}
	
	private class ViewHolder {
	    TextView tvSMS;
	}
}
