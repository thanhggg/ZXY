package com.gggstudio.chuctet.smstet.db;

import android.view.View;
import android.widget.ImageView;

import com.gggstudio.chuctet.smstet.R;


public class CategoryCache {
	private View baseView;
	private ImageView imageCity;

	public CategoryCache(View baseView) {
		this.baseView = baseView;
	}

	public View getViewBase() {
		return baseView;
	}

	public ImageView getImageView(int resource) {
		if (imageCity == null) {
			imageCity = (ImageView) baseView.findViewById(R.id.imgIcon);
		}
		return imageCity;
	}
}
