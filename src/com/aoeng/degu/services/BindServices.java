package com.aoeng.degu.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.aoeng.degu.utils.ViewUtils;

public class BindServices extends Service {
	private MyBinder myBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		ViewUtils.toastCenter(this, "onBind()", false);
		return myBinder;
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

	public class MyBinder extends Binder {
		public BindServices getServices() {
			return BindServices.this;
		}
	}

}
