/**
 * 
 */
package com.aoeng.degu.ui.apps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.AppInfo;
import com.aoeng.degu.services.FileDownlaodService;
import com.aoeng.degu.services.FileUploadService;
import com.aoeng.degu.services.SmsInsertIntoDbServcie;
import com.aoeng.degu.utils.AppUtils;
import com.aoeng.degu.utils.SmsUtils;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * May 19, 2014 9:50:02 AM 應用程序管理界面
 */
public class AppManagerUI extends Activity implements OnClickListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_apps_manager_home);

		this.findViewById(R.id.btnAllApps).setOnClickListener(this);
		this.findViewById(R.id.btnSysApps).setOnClickListener(this);
		this.findViewById(R.id.btnAppSize).setOnClickListener(this);
		this.findViewById(R.id.btnStartOtherApp).setOnClickListener(this);
		this.findViewById(R.id.btnUploadAppsInfo).setOnClickListener(this);
		this.findViewById(R.id.btnUploadSms).setOnClickListener(this);
		this.findViewById(R.id.btnUploadSmsZip).setOnClickListener(this);
		this.findViewById(R.id.btnSaveSmsToSys).setOnClickListener(this);
		this.findViewById(R.id.btnDel).setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAllApps:
			startActivity(new Intent(this, AllAppUI.class));
			break;
		case R.id.btnSysApps:
			startActivity(new Intent(this, SysAppsUI.class));
			break;
		case R.id.btnAppSize:
			startActivity(new Intent(this, AppSizeUI.class));
			break;
		case R.id.btnStartOtherApp:

			start2();
			break;
		case R.id.btnUploadAppsInfo:
			uploadAppInfos();
			break;
		case R.id.btnUploadSms:
			uploadSms(false,null);
			break;
		case R.id.btnUploadSmsZip:
			uploadSms(true,"test");
			break;
		case R.id.btnSaveSmsToSys:
			saveSmsToSys();
			break;
		case R.id.btnDel:
			delSms();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void delSms() {
		// TODO Auto-generated method stub
		// String path = "/storage/sdcard0/df/download/sms.log";
		String path = "";
		if (StringUtils.isEmpty(path)) {
			UIUtils.toastShow("目标文件不存在");
			return;
		}
		File file = new File(path);
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(reader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				LogUtils.e(line);
				String[] items = line.split(", ");
				if (items.length < 5) {
					continue;
				}
				SmsUtils.deleteSmsInSendBoxByDate(getContentResolver(), Long.valueOf(items[3].replaceAll(" ", "")));

			}
			reader.close();
			// FileUtils.deleteDir(file.getAbsolutePath());
			// LogUtils.e("文件" + file.getAbsolutePath() + " delete success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void saveSmsToSys() {
		// TODO Auto-generated method stub
		// SmsUtils.saveSmsToSysDb(getContentResolver(), "123456", "Hello",
		// System.currentTimeMillis());
		Intent smsIntent = new Intent(this, FileDownlaodService.class);
		// String smsUrl =
		// "http://tslogs.qiniudn.com/HWH30-U10-apps-20150109_122921.log";
		String smsUrl = "";
		if (StringUtils.isEmpty(smsUrl)) {
			UIUtils.toastShow("请输入 下载地址");
			return;
		}
		smsIntent.putExtra("fileUrl", smsUrl);
		smsIntent.putExtra("tag", "smsdownload");
		startService(smsIntent);
	}

	/**
	 * 
	 */
	private void uploadAppInfos() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, List<AppInfo>>() {

			@Override
			protected List<AppInfo> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				PackageManager pm = getPackageManager(); // 获得PackageManager对象
				Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
				mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				// 通过查询，获得所有ResolveInfo对象.
				List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);
				// 调用系统排序 ， 根据name排序
				// 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
				Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
				List<AppInfo> mlistAppInfo = new ArrayList<AppInfo>();
				if (mlistAppInfo != null) {
					mlistAppInfo.clear();
					for (ResolveInfo reInfo : resolveInfos) {
						String activityName = reInfo.activityInfo.name; // 获得该应用程序的启动Activity的name
						String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
						String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
						Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
						// 为应用程序的启动Activity 准备Intent
						Intent launchIntent = new Intent();
						launchIntent.setComponent(new ComponentName(pkgName, activityName));
						// 创建一个AppInfo对象，并赋值
						AppInfo appInfo = new AppInfo();
						appInfo.setAppLabel(appLabel);
						appInfo.setPkgName(pkgName);
						appInfo.setAppIcon(icon);
						appInfo.setIntent(launchIntent);
						mlistAppInfo.add(appInfo); // 添加至列表中
					}
				}
				return mlistAppInfo;
			}

			@Override
			protected void onPostExecute(List<AppInfo> result) {
				// TODO Auto-generated method stub
				if (null != result) {
					if (result.isEmpty()) {
						UIUtils.getToastSafe(R.string.app_size_empty);
					} else {
						StringBuffer buffer = new StringBuffer();
						buffer.append(AppUtils.getDeviceInfo()).append("\n");
						for (AppInfo appInfo : result) {
							buffer.append(appInfo.toString());
							buffer.append("\n");
						}
						String fileName = FileUtils.getTempleFilePath("APP", buffer.toString());
						toUploadService(fileName);
					}
				} else {
					UIUtils.getToastSafe(R.string.app_scan_failure);
				}
			}
		}.execute();
	}

	/**
	 * @param b
	 * 
	 */
	private void uploadSms(final boolean b,final String pwd) {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return SmsUtils.getAllSms(getContentResolver());
			}
			/*
			 * (non-Javadoc)
			 * 
			 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
			 */
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				if (!StringUtils.isEmpty(result)) {
					UIUtils.toastShow("Sms scan success !!");
					String fileName = FileUtils.getTempleFilePath("SMS", result, b,pwd);
					toUploadService(fileName);
				} else {
					LogUtils.e("sms content is Empty !!");
				}

			}
		}.execute();

	}

	/**
	 * @param fileName
	 */
	protected void toUploadService(String fileName) {
		// TODO Auto-generated method stub
		Intent smsIntent = new Intent(AppManagerUI.this, FileUploadService.class);
		smsIntent.putExtra("name", fileName);
		startService(smsIntent);
	}

	private void start2() {
		// TODO Auto-generated method stub
		String packageName = "com.DeviceTest";
		Intent intent = new Intent();
		PackageManager packageManager = this.getPackageManager();
		intent = packageManager.getLaunchIntentForPackage(packageName);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
	}

	private void start() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		String packageName = "com.DeviceTest";
		String className = "FirstRun";
		ComponentName cn = new ComponentName(packageName, className);
		intent.setComponent(cn);
		startActivity(intent);
	}

}
