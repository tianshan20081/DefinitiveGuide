/**
 * 
 */
package com.aoeng.degu.ui.imgs;

import java.util.List;

import android.os.AsyncTask;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.aoeng.degu.R;
import com.aoeng.degu.adapter.ImageGroupAdapter;
import com.aoeng.degu.adapter.LocalMusicAdapter;
import com.aoeng.degu.domain.ImageGroup;
import com.aoeng.degu.domain.Music;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.MediaUtils;
import com.aoeng.degu.utils.PhotoUtils;
import com.aoeng.degu.utils.common.LogUtils;

/**
 * @author sczhang 2014年12月6日 下午4:23:45
 * @Email {zhangshch0131@126.com}
 */
public class ImageAutoGroupUI extends BaseUI {

	private ListView lvInfos;
	private Button btnImgsScan;
	private Button btnImgsGroup;
	private Button btnMp3Scan;
	private BaseAdapter mAdapter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnImgsScan:
			loadImags();
			break;
		case R.id.btnImgsGroup:
			imgsAutoGroups();
			break;
		case R.id.btnMp3Scan:
			loadMp3s();
			break;
		}
	}

	/**
	 * 
	 */
	private void loadMp3s() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, List<Music>>() {

			@Override
			protected List<Music> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return MediaUtils.getAllMp3s();
			}

			protected void onPostExecute(List<Music> result) {
				if (null != result) {
					mAdapter = new LocalMusicAdapter(result);
					lvInfos.setAdapter(mAdapter);

					for (Music music : result) {
						LogUtils.e(music.getSize() + "");
					}
				}
			};
		}.execute();
	}

	/**
	 * 
	 */
	private void imgsAutoGroups() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, List<ImageGroup>>() {

			@Override
			protected List<ImageGroup> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return PhotoUtils.getAutoGroupImgs();
			}

			protected void onPostExecute(List<ImageGroup> result) {
				if (null != result) {
					mAdapter = new ImageGroupAdapter(result);
					lvInfos.setAdapter(mAdapter);
				}
			};
		}.execute();
	}

	/**
	 * 
	 */
	private void loadImags() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_imgs_autogroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		lvInfos = (ListView) findView(R.id.lvInfos);
		btnImgsScan = (Button) findView(R.id.btnImgsScan);
		btnImgsGroup = (Button) findView(R.id.btnImgsGroup);
		btnMp3Scan = (Button) findView(R.id.btnMp3Scan);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnImgsGroup.setOnClickListener(this);
		btnImgsScan.setOnClickListener(this);
		btnMp3Scan.setOnClickListener(this);
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
