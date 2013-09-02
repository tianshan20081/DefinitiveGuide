/**
 * 
 */
package com.aoeng.degu.ui;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.cv.BidirSlidingUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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

		default:
			break;
		}
	}

}
