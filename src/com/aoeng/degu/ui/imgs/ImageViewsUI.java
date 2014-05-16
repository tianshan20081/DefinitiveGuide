/**
 * 
 */
package com.aoeng.degu.ui.imgs;

import com.aoeng.degu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * May 16, 2014  6:02:25 PM
 *
 */
public class ImageViewsUI extends Activity implements OnClickListener {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ui_imgs_home);
		
		this.findViewById(R.id.btnCircleImg).setOnClickListener(this);
		this.findViewById(R.id.btnShapeImg).setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCircleImg:
			startActivity(new Intent(ImageViewsUI.this, CircleImgUI.class));
			break;
		case R.id.btnShapeImg:
			startActivity(new Intent(ImageViewsUI.this, CustomShapeImgUI.class));
			break;

		default:
			break;
		}
	}
}
