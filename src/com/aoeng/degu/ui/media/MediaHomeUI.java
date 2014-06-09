/**
 * 
 */
package com.aoeng.degu.ui.media;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Jun 9, 2014  3:04:23 PM
 *
 */
public class MediaHomeUI extends BaseUI {

	private Button btnVideoView;

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnVideoView:
			startActivity(new Intent(this,VideoViewUI.class));
			break;
		case R.id.btnSurfaceView:
			startActivity(new Intent(this, SurfaceViewUI.class));
			break;
		case R.id.btnAudioPlay:
			startActivity(new Intent(this, AudioPlayUI.class));
			break;
		case R.id.btnSoundPool:
			startActivity(new Intent(this, SoundPoolUI.class));
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_media_home);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnVideoView = (Button)this.findView(R.id.btnVideoView);
		findView(R.id.btnSurfaceView).setOnClickListener(this);
		findView(R.id.btnAudioPlay).setOnClickListener(this);
		findView(R.id.btnSoundPool).setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnVideoView.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		
	}

}
