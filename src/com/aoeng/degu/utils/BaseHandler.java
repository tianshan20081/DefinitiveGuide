/**
 * 
 */
package com.aoeng.degu.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.aoeng.degu.R;
import com.aoeng.degu.services.DataCallback;
import com.aoeng.degu.ui.BaseUI;

/**
 * May 21, 2014 5:11:23 PM
 * 
 */
public class BaseHandler extends Handler {
	private static final String TAG = BaseHandler.class.getName();
	private Context context;
	private DataCallback callBack;
	private RequestVO reqVo;
	private ProgressDialog progressDialog;

	public BaseHandler(Context context, DataCallback callBack, RequestVO reqVo) {
		this.context = context;
		this.callBack = callBack;
		this.reqVo = reqVo;
	}

	public void handleMessage(Message msg) {
		closeProgressDialog();
		if (msg.what == Constant.SUCCESS) {
			if (msg.obj == null) {
				CommonUtil.showInfoDialog(context, context.getString(R.string.net_error));
			} else {
				callBack.processData(msg.obj, true);
			}
		} else if (msg.what == Constant.NET_FAILED) {
			CommonUtil.showInfoDialog(context, context.getString(R.string.net_error));
		} else if (msg.what == Constant.NET_ERROR) {
			CommonUtil.showInfoDialog(context, context.getString(R.string.net_error));
		}

		Logger.d(TAG, "recordSize:" + BaseUI.record.size());
	}

	/**
	 * 显示提示框
	 */
	protected void showProgressDialog() {
		if ((!((Activity) context).isFinishing()) && (this.progressDialog == null)) {
			this.progressDialog = new ProgressDialog(context);
		}
		this.progressDialog.setTitle("标题");
		this.progressDialog.setMessage("数据加载中...");
		this.progressDialog.show();
	}

	/**
	 * 关闭提示框
	 */
	protected void closeProgressDialog() {
		if (this.progressDialog != null)
			this.progressDialog.dismiss();
	}
}
