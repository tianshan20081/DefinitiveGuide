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
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;

import com.aoeng.degu.services.LogFileUploadServices;
import com.aoeng.degu.services.MulityLogFileUploadServices;
import com.aoeng.degu.utils.AppUtils;
import com.aoeng.degu.utils.ThreadPoolManager;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * May 21, 2014 4:54:26 PM
 * 
 */
public class DGApplication extends Application {
	private static DGApplication mApplication;

	/** 缓存路径 */
	private static String cacheDir;

	private static Looper mMainThreadLooper;

	private static int mMainThreadId;

	private List<Activity> records = new ArrayList<Activity>();

	private static ThreadPoolManager mThreadPoolManager;

	private static Thread mMainThread;
	private static final String TAG = "HGApplication";

	@Override
	public void onCreate() {
		super.onCreate();

		initCacheDirPath();
		this.mApplication = this;
		this.mMainThreadLooper = getMainThreadLooper();
		this.mMainThreadId = android.os.Process.myPid();
		this.mMainThread = Thread.currentThread();

		mThreadPoolManager = ThreadPoolManager.getInstance();

		// CrashHandler mCrashHandler = CrashHandler.getInstance();
		// mCrashHandler.init();
		// Intent intent = new Intent(UIUtils.getContext(),
		// LogFileUploadServices.class);
		Intent intent = new Intent(UIUtils.getContext(), MulityLogFileUploadServices.class);
		getContext().startService(intent);

		printCommonInfo();
	}

	private void printCommonInfo() {
		// TODO Auto-generated method stub
		LogUtils.e("---AppKey----" + AppUtils.getAppKey(this.mApplication));
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

	public static Context getContext() {
		// TODO Auto-generated method stub
		return mApplication;
	}

	public static Looper getMainThreadLooper() {
		// TODO Auto-generated method stub
		return mMainThreadLooper;
	}

	public static int getMainThreadId() {
		// TODO Auto-generated method stub
		return mMainThreadId;
	}

	public static Thread getMainThread() {
		return mMainThread;
	}

	public static ThreadPoolManager getThreadPoolManager() {
		// TODO Auto-generated method stub
		return mThreadPoolManager;
	}
}
