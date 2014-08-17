package com.aoeng.degu.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.aoeng.degu.application.DGApplication;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.chartengine.SimpleChartEngineUI;

public class UIUtils {

	public static Context getContext() {
		// TODO Auto-generated method stub

		return DGApplication.getContext();
	}

	public static void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		BaseUI ui = BaseUI.getCurrenthowUI();
		if (null != ui) {
			ui.startActivity(intent);
		} else {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(intent);
		}
	}

	public static void getToastSafe(int resId) {
		getToastSafe(getString(resId));
	}

	private static String getString(int resId) {
		// TODO Auto-generated method stub
		return getContext().getString(resId);
	}

	public static void getToastSafe(final String msg) {
		// TODO Auto-generated method stub
		if (isRunInMainThread()) {
			toastShow(msg);
		} else {
			getHandler().post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					toastShow(msg);
				}

			});
		}
	}

	private static boolean isRunInMainThread() {
		// TODO Auto-generated method stub

		return android.os.Process.myPid() == DGApplication.getMainThreadId();
	}

	private static Handler getHandler() {
		// TODO Auto-generated method stub
		Looper mainLooper = DGApplication.getMainThreadLooper();
		Handler mainHandler = new Handler(mainLooper);
		return mainHandler;
	}

	public static void toastShow(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
	}

	public static void post(Runnable run) {
		getHandler().post(run);
	}

	public static void runInMainThread(Runnable run) {
		// TODO Auto-generated method stub
		if (isRunInMainThread()) {
			run.run();
		} else {
			post(run);
		}

	}

	public static void startActivity(Class<SimpleChartEngineUI> clazz) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getContext(), clazz));
	}
	
	public static ThreadPoolManager getThreadPoolManager(){
		return DGApplication.getThreadPoolManager();
	}
}
