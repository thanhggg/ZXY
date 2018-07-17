package com.gggstudio.chuctet.smstet.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gggstudio.chuctet.smstet.R;
import com.gggstudio.chuctet.smstet.db.SMSObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SMSViewpagerAdapter extends PagerAdapter {

	private Context context = null;
	private ArrayList<SMSObject> arr = null;

	public static TextView tvPager;
	public static LinearLayout layout1, layout2;

	public SMSViewpagerAdapter(Context context, ArrayList<SMSObject> arr) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arr = arr;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object instantiateItem(View container, int position) {

		LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(context);

		LinearLayout v = (LinearLayout) inflater.inflate(
				R.layout.item_sms_viewpager, null);

		SMSObject sms1 = arr.get(position);

		TextView tvSMSView = (TextView) v.findViewById(R.id.tvSMSView);
		tvPager = (TextView) v.findViewById(R.id.tvnumPages);
		layout1 = (LinearLayout) v.findViewById(R.id.layoutSms1);
		layout2 = (LinearLayout) v.findViewById(R.id.layoutSms2);

		tvPager.setText((position + 1) + " / " + arr.size());

		tvSMSView.setText(sms1.getContent());
		String temp = Normalizer.normalize(sms1.getContent().toString(),
				Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		final String khongdau = pattern.matcher(temp).replaceAll("")
				.replaceAll("Đ", "D").replaceAll("đ", "d");
		final String codau=sms1.getContent().toString();
		layout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Dialog(codau,khongdau);
			}
		});
		
		((ViewPager) container).addView(v);
		return v;
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	public void Dialog(final String codau, final String khongdau) {
		new AlertDialog.Builder(context)
				.setTitle("Send SMS")
				.setMessage("Bạn muốn gửi tin nhắn có dấu hay không dấu ?")
				.setPositiveButton("không dấu",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								sendSMS(khongdau);
								dialog.dismiss();
							}
						})
				.setNegativeButton("có dấu",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								sendSMS(codau);
								dialog.dismiss();
							}
						}).show();
	}

	private void sendSMS(String sms) {
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		sendIntent.putExtra("sms_body", sms);
		sendIntent.setType("vnd.android-dir/mms-sms");
		context.startActivity(sendIntent); 
	}
}
