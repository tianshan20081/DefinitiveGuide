package com.aoeng.degu.utils.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.application.DGApplication;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.mp.MpRequestInfoUI;
import com.aoeng.degu.utils.ThreadPoolManager;

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

	public static String getString(int resId) {
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

	public static void startActivity(Class clazz) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getContext(), clazz));
	}

	public static ThreadPoolManager getThreadPoolManager() {
		return DGApplication.getThreadPoolManager();
	}

	public static void notify(String extrs) {
		// TODO Auto-generated method stub
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext()).setSmallIcon(R.drawable.ic_launcher).setContentTitle("来自京东服务器的通知").setContentText(extrs);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(getContext(), MpRequestInfoUI.class);
		resultIntent.putExtra("extras", extrs);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MpRequestInfoUI.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) UIUtils.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.

		mBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
		mBuilder.setLights(0xff00ff00, 300, 1000);
		long[] pattern = { 0, 100, 200, 300 };
		mBuilder.setVibrate(pattern);
		mNotificationManager.notify(2, mBuilder.build());
	}
}
