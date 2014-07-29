/**
 * 
 */
package com.aoeng.degu.ui.encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.EncryptionUtils;

/**
 * Jul 22, 2014 1:55:41 PM
 * 
 */
public class EncHomeUI extends BaseUI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnDesEnc:
			String src = "张三";
			try {
				toast("encode ------ >" + EncryptionUtils.getDesEnctry(URLEncoder.encode(src, "gbk"), "testtest"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btnDesDec:
			toast("desc deconde ");
			try {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String des = EncryptionUtils.getDesEnctry("zhangsan", "testtest");
						System.out.println("deconde ----->" + EncryptionUtils.getDesDec(des, "texttext"));
					}
				}).start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_enc_home);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findView(R.id.btnDesEnc).setOnClickListener(this);
		findView(R.id.btnDesDec).setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
