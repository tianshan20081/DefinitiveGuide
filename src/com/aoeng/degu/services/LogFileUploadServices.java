package com.aoeng.degu.services;

import java.io.File;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.aoeng.degu.utils.FileUtils;
import com.aoeng.degu.utils.Logger;

import android.app.Service;
import android.app.backup.FileBackupHelper;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

public class LogFileUploadServices extends Service {

	private static final String TAG = LogFileUploadServices.class.getName();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		String path = FileUtils.getAppCrashPath();
		Logger.i(TAG, path);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String path = FileUtils.getAppCrashPath();
		if (!TextUtils.isEmpty(path)) {
			File fileCrash = new File(path);
			if (fileCrash.exists() && fileCrash.isDirectory() && fileCrash.list().length > 0) {
				String[] loges = fileCrash.list();
				for (String log : loges) {
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost();
				}
			}
		}
		return START_STICKY;
	}

}
