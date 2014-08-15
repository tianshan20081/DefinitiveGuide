package com.aoeng.degu.ui.phonegap;

import com.phonegap.DroidGap;

import android.os.Bundle;

public class PhoneGapCameraUI extends DroidGap {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		super.loadUrl("file:///android_asset/www/pgcamera.html");
	}

}
