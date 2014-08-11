package com.aoeng.degu.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.HomeUI;
import com.aoeng.degu.utils.FileUtils;
import com.aoeng.degu.utils.Logger;
import com.aoeng.degu.utils.UIUtils;

public class CrashHandler implements UncaughtExceptionHandler {
	private static final String TAG = CrashHandler.class.getName();
	private static CrashHandler mCrashHandler = new CrashHandler();
	private UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
	private Map<String, String> mInfos = new HashMap<String, String>();

	private SimpleDateFormat crashLogNameDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd-HH");
	private SimpleDateFormat logsDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss-SSS");

	private CrashHandler() {
		// TODO Auto-generated constructor stub
	}

	public static CrashHandler getInstance() {
		return mCrashHandler;
	}

	public void init() {
		mDefaultUncaughtExceptionHandler = Thread
				.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		if (!handleException(ex) && null != mDefaultUncaughtExceptionHandler) {
			mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
		} else {
			SystemClock.sleep(3000);
			BaseUI.exit();
			// 在一个if-else判断中，如果我们程序是按照我们预想的执行，到最后我们需要停止程序，那么我们使用System.exit(0)，而System.exit(1)一般放在catch块中，当捕获到异常，需要停止程序，我们使用System.exit(1)。这个status=1是用来表示这个程序是非正常退出。
			System.exit(1);

			Intent intent = new Intent(UIUtils.getContext(), HomeUI.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			UIUtils.startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	private boolean handleException(Throwable ex) {
		// TODO Auto-generated method stub
		if (null == ex) {
			return false;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Looper.prepare();
				UIUtils.getToastSafe(R.string.exception_exit);
				Looper.loop();
			}
		}).start();

		collectionDeviceInfo();

		saveInfos2SDCard(ex);
		return true;
	}

	private void saveInfos2SDCard(Throwable ex) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : mInfos.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue())
					.append("\n");
		}
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (null != cause) {
			ex.printStackTrace(printWriter);
			cause = ex.getCause();
		}
		String exceptionInfo = logsDateFormat.format(new Date()).concat("\n")
				.concat(writer.toString());
		String fileName = "crash-" + crashLogNameDateFormat.format(new Date())
				+ ".log";
		String crashPath = FileUtils.getAppCrashPath();
		if (!TextUtils.isEmpty(crashPath)) {
			File crashDir = new File(crashPath);
			if (!crashDir.exists()) {
				crashDir.mkdirs();
			}
			File logFile = new File(crashPath.concat(File.separator).concat(
					fileName));
			if (logFile.exists()) {
				sb = new StringBuffer(exceptionInfo);
			} else {
				sb.append(exceptionInfo);
			}
			try {
				FileOutputStream fos = new FileOutputStream(logFile, true);
				fos.write(sb.toString().getBytes());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logger.e(TAG, "log file save to sd card error !");
			}
		}
	}

	private void collectionDeviceInfo() {
		// TODO Auto-generated method stub
		PackageManager pm = UIUtils.getContext().getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(UIUtils.getContext()
					.getPackageName(), PackageManager.GET_ACTIVITIES);
			if (null != pi) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				mInfos.put("versionName", versionName);
				mInfos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.e(TAG, "get application info error ");
		}
		try {
			Field[] fields = Build.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				mInfos.put(field.getName(), field.get(null).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.e(TAG, "Reflect Build error");
		}
	}

}
