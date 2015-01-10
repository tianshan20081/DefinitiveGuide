/**
 * 
 */
package com.aoeng.degu.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.http.Header;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.aoeng.degu.utils.SmsUtils;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;
import com.aoeng.degu.utils.net.asyncthhpclient.AsyncHttpClient;
import com.aoeng.degu.utils.net.asyncthhpclient.FileAsyncHttpResponseHandler;

/**
 * @author sczhang 2015年1月9日 下午4:43:29
 */
public class FileDownlaodService extends Service {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if (null != intent) {
			if ("smsdownload".equalsIgnoreCase(intent.getStringExtra("tag"))) {
				String fileUrl = intent.getStringExtra("fileUrl");
				if (!StringUtils.isEmpty(fileUrl)) {
					UIUtils.toastShow("开始下载短信息");
					beginDownlaod(fileUrl);
				} else {

				}
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * @param fileUrl
	 */
	private void beginDownlaod(String fileUrl) {
		// TODO Auto-generated method stub
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.setTimeout(5000);
		File smsFile = new File(FileUtils.getDownloadDir(), "sms.log");
		asyncHttpClient.get(fileUrl, new FileAsyncHttpResponseHandler(smsFile) {

			@Override
			public void onSuccess(int statusCode, Header[] headers, File file) {
				// TODO Auto-generated method stub
				LogUtils.e("文件下载 成功   开始 写入到数据库" + file.getAbsolutePath());
				// 文件下载完成 开始写入
				insertIntoSmsDb(file.getAbsolutePath());
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
				// TODO Auto-generated method stub
				LogUtils.e("文件下载 失败" + file.getAbsolutePath());
			}
		});
	}

	/**
	 * @param absolutePath
	 */
	protected void insertIntoSmsDb(String smsPath) {
		// TODO Auto-generated method stub
		File file = new File(smsPath);
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
				String address = items[0].trim();
				String msg = items[2].trim();
				long date = Long.valueOf(items[3].replaceAll(" ", ""));
				String type = items[4].trim();
				boolean isSend = "接收".equalsIgnoreCase(type) ? false : true;
				SmsUtils.saveSmsToSysDb(getContentResolver(), address, msg, date, isSend);

			}
			reader.close();
			FileUtils.deleteDir(file.getAbsolutePath());
			LogUtils.e("文件" + file.getAbsolutePath() + " delete success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
