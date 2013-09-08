/**
 * 
 */
package com.aoeng.degu.ui.cv;

import com.aoeng.degu.R;
import com.aoeng.degu.adapter.PhotoWallTwoCacheAdapter;
import com.aoeng.degu.utils.ImagesURL;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

/**
 * @author [ShaoCheng Zhang] Sep 6, 2013:4:26:35 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoWall2CacheUI extends Activity {
	private ListView lvPhotoWall2Cache;
	private PhotoWallTwoCacheAdapter adapter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_cv_pthoto2cache);

		lvPhotoWall2Cache = (ListView) this.findViewById(R.id.lvPhotoWall2Cache);
		adapter = new PhotoWallTwoCacheAdapter(this, ImagesURL.imageThumbUrls);
		lvPhotoWall2Cache.setAdapter(adapter);

		lvPhotoWall2Cache.setOnScrollListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_FLING:
					// 滑动中
					adapter.setFlagBusy(true);
					break;
				case OnScrollListener.SCROLL_STATE_IDLE:
					// 停止滑动
					adapter.setFlagBusy(false);
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					// 用户手指还在屏幕上面
					adapter.setFlagBusy(false);
					break;
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

	}

}
