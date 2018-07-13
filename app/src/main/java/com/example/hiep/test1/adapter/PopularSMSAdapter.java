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
    private Context context = null;
    private LayoutInflater inflater;
    private ArrayList<SMSObject> arr = null;
    private OnItemClickListener mListener;

    public PopularSMSAdapter(Context context, int resouce, ArrayList<SMSObject> list) {
        this.context = context;
        this.resouce = resouce;
        inflater = LayoutInflater.from(context);
        this.arr = list;
    }

    public interface OnItemClickListener {

        void onItemClicked(int pos, String msg);
    }

    @Override
    public int getCount() {
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

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = new ViewHolder();
        final SMSObject sms = arr.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(resouce, null);
            mHolder.tvSMS = convertView.findViewById(R.id.tvSMSName);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        if (sms != null) {
            mHolder.tvSMS.setTextColor(context.getResources().getColor(R.color.red));
            mHolder.tvSMS.setText(sms.getContent());

            mHolder.tvSMS.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClicked(position, sms.getContent());
                    }
                }
            });
        }

        return convertView;
    }

    private class ViewHolder {
        TextView tvSMS;
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
