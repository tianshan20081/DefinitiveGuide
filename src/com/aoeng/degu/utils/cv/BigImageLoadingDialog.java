/**
 * 
 */
package com.aoeng.degu.utils.cv;

import com.aoeng.degu.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * @author [ShaoCheng Zhang] Sep 8, 2013:2:09:18 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class BigImageLoadingDialog extends Dialog {

	/**
	 * @param context
	 */
	public BigImageLoadingDialog(Context context) {
		super(context, R.style.bigImageLoadingStyle);
		// TODO Auto-generated constructor stub
	}

	private BigImageLoadingDialog(Context context, int theme) {
		super(context, theme);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Dialog#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_imageloading);
	}

}
