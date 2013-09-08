/**
 * 
 */
package com.aoeng.degu.ui.cv;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.cv.BigImageLoadingDialog;

/**
 * @author [ShaoCheng Zhang] Sep 8, 2013:2:06:07 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class BigImageShowerUI extends Activity {

	private BigImageLoadingDialog imageLoadingDialog;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_cv_bigimage_shower);

		imageLoadingDialog = new BigImageLoadingDialog(BigImageShowerUI.this);
		imageLoadingDialog.show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				imageLoadingDialog.dismiss();
			}
		}, 1000 * 2);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		finish();
		return true;
	}
}
