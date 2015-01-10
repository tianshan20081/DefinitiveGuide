/**
 * 
 */
package com.aoeng.degu.services;

import java.io.File;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;
import com.aoeng.degu.utils.qiniu.QNApi;
import com.qiniu.io.IO;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadCallRet;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

/**
 * @author sczhang 2015年1月9日 下午1:48:33
 */
public class FileUploadService extends Service {

	protected static final int UPLOAD_SUCCESS = 2;
	protected static final int UPLOAD_FAILURE = 3;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPLOAD_SUCCESS:
				UIUtils.toastShow("上传成功");
				String tempPath = (String) msg.obj;
				if (!StringUtils.isEmpty(tempPath)) {
					if (FileUtils.deleteDir(tempPath)) {
						UIUtils.getToastSafe(R.string.tempfile_del_success);
						LogUtils.i(tempPath);
						stopSelf();
					}
				}
				break;
			case UPLOAD_FAILURE:
				UIUtils.toastShow("上传失败");
				break;
			default:
				break;
			}
		};

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if (null != intent) {
			String uploadFileName = intent.getStringExtra("name");
			if (!StringUtils.isEmpty(uploadFileName)) {
				File uploadFile = new File(uploadFileName);
				if (uploadFile.exists() && uploadFile.isFile()) {
					UIUtils.toastShow("FileUploadService----begin upload");
					startUploadInfos(uploadFile);
				}
			}

		}
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * @param uploadFile
	 */
	private void startUploadInfos(final File uploadFile) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		IO.putFile(QNApi.getAuthorizer(QNApi.BUCKET_TSLOGS), uploadFile.getName(), uploadFile, new PutExtra(), new CallBack() {

			@Override
			public void onSuccess(UploadCallRet ret) {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				msg.what = UPLOAD_SUCCESS;
				msg.obj = uploadFile.getAbsolutePath();
				handler.sendMessage(msg);
				LogUtils.e(QNApi.BUCKET_TSLOGS+ret.toString());
			}

			@Override
			public void onProcess(long current, long total) {
				// TODO Auto-generated method stub
				LogUtils.i("upload procress " + (current * 100 / total) + "%");
			}

			@Override
			public void onFailure(CallRet ret) {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				msg.what = UPLOAD_FAILURE;
				msg.obj = ret.toString();
				handler.sendMessage(msg);
				LogUtils.i(ret.toString());
			}
		});

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
