package com.example.hiep.test1.db;

import android.view.View;
import android.widget.ImageView;

import com.example.hiep.test1.R;


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
