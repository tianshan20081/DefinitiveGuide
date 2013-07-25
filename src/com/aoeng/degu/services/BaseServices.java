package com.aoeng.degu.services;

import com.aoeng.degu.utils.ViewUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BaseServices extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ViewUtils.toastCenter(this, "onCreate()", false);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);

		ViewUtils.toastCenter(this, "onStart()", false);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		ViewUtils.toastCenter(this, "onRebind()", false);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ViewUtils.toastCenter(this, "onDestroy()", false);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		ViewUtils.toastCenter(this, "onUnbind()", false);
		return super.onUnbind(intent);

	}

}
