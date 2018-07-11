package com.example.hiep.test1;


import android.app.Activity;
import android.os.Bundle;

public class ActivityBase extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.popup_right_in, R.anim.popup_left_out);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.popup_right_in, R.anim.popup_left_out);
	}
}
