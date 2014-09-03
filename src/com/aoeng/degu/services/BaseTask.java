/**
 * 
 */
package com.aoeng.degu.services;

import android.net.wifi.WifiConfiguration.Status;
import android.os.Handler;
import android.os.Message;

import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.Constant;
import com.aoeng.degu.utils.Logger;
import com.aoeng.degu.utils.RequestVO;
import com.aoeng.degu.utils.UIUtils;
import com.aoeng.degu.utils.net.NetUtil;

/**
 * May 21, 2014 5:19:24 PM
 * 
 */
public class BaseTask implements Runnable {
	private RequestVO reqVo;
	private Handler handler;

	public BaseTask(RequestVO reqVo, Handler handler) {
		this.reqVo = reqVo;
		this.handler = handler;
	}

	@Override
	public void run() {
		Object obj = null;
		Message msg = Message.obtain();
		try {
			if (NetUtil.hasNetwork(UIUtils.getContext())) {
				obj = NetUtil.post(reqVo);
				if (null != obj) {
					Logger.i(BaseUI.TAG, obj.toString());
					if (obj instanceof Status) {
						// Intent intent = new Intent(BaseUI.this,
						// LoginUI.class);
						// intent.putExtra("notlogin", "notlogin");
						// startActivityForResult(intent, NOT_LOGIN);
					} else {
						msg.what = Constant.SUCCESS;
						msg.obj = obj;
						handler.sendMessage(msg);
						BaseUI.record.remove(this);
					}
				} else {
					msg.what = Constant.NET_ERROR;
					msg.obj = obj;
					handler.sendMessage(msg);
					BaseUI.record.remove(this);
				}
			} else {
				msg.what = Constant.NET_FAILED;
				msg.obj = obj;
				handler.sendMessage(msg);
				BaseUI.record.remove(this);
			}
		} catch (Exception e) {
			BaseUI.record.remove(this);
			throw new RuntimeException(e);
		}
	}

}