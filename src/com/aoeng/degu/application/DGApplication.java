/**
 * 
 */
package com.aoeng.degu.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;

import com.aoeng.degu.utils.Logger;

/**
 * May 21, 2014 4:54:26 PM
 * 
 */
public class DGApplication extends Application {

	/** 缓存路径 */
	private static String cacheDir;

	private List<Activity> records = new ArrayList<Activity>();
	private static final String TAG = "HGApplication";

	@Override
	public void onCreate() {
		super.onCreate();

		initCacheDirPath();
	}

	public static String getCacheDirPath() {
		return cacheDir;
	}

	private void initCacheDirPath() {
		File f;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			f = new File(Environment.getExternalStorageDirectory() + "/.huigush/");
			if (!f.exists()) {
				f.mkdir();
			}
		} else {
			f = getApplicationContext().getCacheDir();
		}
		cacheDir = f.getAbsolutePath();
	}

	private class ECServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Logger.d(TAG, "onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	}

	public void addActvity(Activity activity) {
		records.add(activity);
		Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
	}

	public void removeActvity(Activity activity) {
		records.remove(activity);
		Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
	}

	public void exit() {
		for (Activity activity : records) {
			activity.finish();
		}
	}

	public int getCurrentActivitySize() {
		return records.size();
	}
}