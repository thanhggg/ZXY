package com.example.hiep.test1.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hiep.test1.R;
import com.example.hiep.test1.db.CategoryObject;
import com.example.hiep.test1.db.SMSObject;
import java.util.ArrayList;

public class PopularSMSAdapter extends BaseAdapter {
	private int resouce;
	Context context = null;
	 private LayoutInflater inflater;
	ArrayList<SMSObject> arr = null;
	
	public PopularSMSAdapter(Context context, int resouce, ArrayList<SMSObject> list) {
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
			mHolder.tvSMS = (TextView) convertView.findViewById(R.id.tvSMSName);
			convertView.setTag(mHolder);
		}else{
			mHolder = (ViewHolder) convertView.getTag();
		}
		
		if(sms != null){
			if(position % 2 == 0){
				mHolder.tvSMS.setBackgroundResource(R.drawable.bg_item_red);
				mHolder.tvSMS.setTextColor(Color.WHITE);
			}else{
				mHolder.tvSMS.setBackgroundResource(R.drawable.bg_item_white);
				mHolder.tvSMS.setTextColor(Color.BLACK);
			}
			mHolder.tvSMS.setText(sms.getContent());
		}
		
		
		return convertView;
	}
	
	private class ViewHolder {
	    TextView tvSMS;
	}
}
