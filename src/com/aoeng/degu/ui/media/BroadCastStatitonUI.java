/**
 * 
 */
package com.aoeng.degu.ui.media;

import android.view.View;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * @author sczhang 2015年1月30日 下午5:44:35
 */
public class BroadCastStatitonUI extends BaseUI {

	private Button btnPlay;
	private Button btnChoose;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnChoose:
			
			break;
		case R.id.btnPlay:
//			String isInstallerString = VitamioInstaller.checkVitamioInstallation(NetRadioDemoActivity.this);//检查插件是否安装成功,这里是一个费时操作,应该启新线程处理,作为一个demo我就不做了
//			Log.i("tag",isInstallerString); //插件安装成功后,Log中显示插件名称
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_media_brosta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnChoose = (Button) findView(R.id.btnChoose);
		btnPlay = (Button) findView(R.id.btnPlay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnPlay.setOnClickListener(this);
		btnChoose.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
