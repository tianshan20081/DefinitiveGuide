/**
 * 
 */
package com.aoeng.degu.ui.media;

import android.content.Intent;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * Jun 9, 2014  3:04:23 PM
 *
 */
public class MediaHomeUI extends BaseUI {


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCamera:
			startActivity(new Intent(this, CameraUI.class));
			break;
		case R.id.btnIntentCamera:
			startActivity(new Intent(this, IntentCameraUI.class));
			break;
		case R.id.btnRawAudio:
			startActivity(new Intent(this, RawAudioUI.class));
			break;
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
		case R.id.btnIntentVideoCamera:
			startActivity(new Intent(this, IntentVideoCameraUI.class));
			break;
		case R.id.btnRecorderVoidce:
			startActivity(new Intent(this, MediaRecorderedVoiceUI.class));
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
		findView(R.id.btnVideoView).setOnClickListener(this);
		findView(R.id.btnSurfaceView).setOnClickListener(this);
		findView(R.id.btnAudioPlay).setOnClickListener(this);
		findView(R.id.btnSoundPool).setOnClickListener(this);
		findView(R.id.btnRawAudio).setOnClickListener(this);
		findView(R.id.btnIntentCamera).setOnClickListener(this);
		findView(R.id.btnCamera).setOnClickListener(this);
		findView(R.id.btnIntentVideoCamera).setOnClickListener(this);
		findView(R.id.btnRecorderVoidce).setOnClickListener(this);
		
	}

	/* (non-Javadoc)
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		
	}

}
