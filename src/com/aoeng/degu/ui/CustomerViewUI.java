/**
 * 
 */
package com.aoeng.degu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.cv.BidirSlidingUI;
import com.aoeng.degu.ui.cv.Contacts2UI;
import com.aoeng.degu.ui.cv.ContactsUI;
import com.aoeng.degu.ui.cv.PhotoWall2CacheUI;
import com.aoeng.degu.ui.cv.PhotoWallUI;
import com.aoeng.degu.ui.cv.PhotoWaterFallUI;

/**
 * @author [Aoeng Zhang] @datatime Sep 2, 2013:4:24:12 PM
 * @Email [zhangshch2008@gmail.com] Sep 2, 2013
 */
public class CustomerViewUI extends Activity implements OnClickListener {
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_cv_home);

		this.findViewById(R.id.btnBiDirSlid).setOnClickListener(this);
		this.findViewById(R.id.btnPhotoWall).setOnClickListener(this);
		this.findViewById(R.id.btnContacts1).setOnClickListener(this);
		this.findViewById(R.id.btnContacts2).setOnClickListener(this);
		this.findViewById(R.id.btnPhotoWall2Cache).setOnClickListener(this);
		this.findViewById(R.id.btnPhotoWaterfall).setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnBiDirSlid:
			intent = new Intent(CustomerViewUI.this, BidirSlidingUI.class);
			startActivity(intent);
			break;
		case R.id.btnPhotoWall:
			intent = new Intent(CustomerViewUI.this, PhotoWallUI.class);
			startActivity(intent);
			break;
		case R.id.btnContacts1:
			intent = new Intent(CustomerViewUI.this, ContactsUI.class);
			startActivity(intent);
			break;
		case R.id.btnContacts2:
			intent = new Intent(CustomerViewUI.this, Contacts2UI.class);
			startActivity(intent);
			break;
		case R.id.btnPhotoWall2Cache:
			intent = new Intent(CustomerViewUI.this, PhotoWall2CacheUI.class);
			startActivity(intent);
			break;
		case R.id.btnPhotoWaterfall:
			intent = new Intent(CustomerViewUI.this, PhotoWaterFallUI.class);
			startActivity(intent);
			break;
		}
	}

}
