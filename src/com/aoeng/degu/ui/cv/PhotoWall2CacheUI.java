/**
 * 
 */
package com.aoeng.degu.ui.cv;

import com.aoeng.degu.R;
import com.aoeng.degu.adapter.PhotoWallTwoCacheAdapter;
import com.aoeng.degu.utils.ImagesURL;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * @author [ShaoCheng Zhang] Sep 6, 2013:4:26:35 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoWall2CacheUI extends Activity {
	private ListView lvPhotoWall2Cache;

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
		PhotoWallTwoCacheAdapter adapter = new PhotoWallTwoCacheAdapter(this, ImagesURL.imageThumbUrls);
		lvPhotoWall2Cache.setAdapter(adapter);
	}

}
