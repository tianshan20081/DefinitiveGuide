/**
 * 
 */
package com.aoeng.degu.ui;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.application.DGApplication;
import com.aoeng.degu.services.BaseTask;
import com.aoeng.degu.services.DataCallback;
import com.aoeng.degu.utils.BaseHandler;
import com.aoeng.degu.utils.RequestVO;
import com.aoeng.degu.utils.ThreadPoolManager;

/**
 * May 21, 2014 4:48:31 PM
 * 
 */
public abstract class BaseUI extends Activity implements View.OnClickListener {
	private static final int BITS_PER_UNIT = 8;
	public static final String TAG = BaseUI.class.getName();
	private LinearLayout fmContent;
	private DGApplication application;
	public static List<BaseTask> record = new Vector<BaseTask>();
	private ThreadPoolManager threadPoolManager;
	/** 登录请求码 */
	public static final int NOT_LOGIN = 403;

	/** 登录结果码 */
	public static final int LOGIN_SUCCESS = 10000000;
	/** ContentView */
	private View inflate;
	protected Context context;

	public BaseUI() {
		threadPoolManager = ThreadPoolManager.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		application = (DGApplication) getApplication();
		application.addActvity(this);
		super.setContentView(R.layout.ui_base);

		fmContent = (LinearLayout) super.findViewById(R.id.fmContent);
		context = getApplicationContext();
		initView();
	}

	/**
	 * 
	 */
	private void initView() {
		// TODO Auto-generated method stub
		loadViewLayout();
		findViewById();
		setListener();
		processLogic();
		int i = position(3);

		// toast(String.valueOf(i));
	}

	private int position(int idx) { // bits big-endian in each unit
		return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));
	}

	/**
	 * @param i
	 */
	protected <T extends View> T getViewById(int i) {
		// TODO Auto-generated method stub

		return (T) findViewById(i);
	}

	/**
	 * 设置资源页面
	 */
	@Override
	public void setContentView(int layoutResID) {
		inflate = getLayoutInflater().inflate(layoutResID, null);
		setContentView(inflate);
	}

	public void setContentView(View view) {
		fmContent.removeAllViews();
		fmContent.addView(inflate);
	};

	/**
	 * 根据资源 id 查找资源
	 * 
	 * @param id
	 * @return
	 */
	public <T extends View> T findView(int id) {
		return (T) inflate.findViewById(id);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == NOT_LOGIN) {
			if (resultCode == LOGIN_SUCCESS) {
				int size = record.size();
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						threadPoolManager.addTask(record.get(0));
					}
				}
			} else {
				finish();
			}
		}
	}

	/**
	 * 从服务器上获取数据，并回调处理
	 * 
	 * @param reqVo
	 * @param callBack
	 */
	protected void getDataFromServer(RequestVO reqVo, DataCallback callBack) {
		BaseHandler handler = new BaseHandler(this, callBack, reqVo);
		BaseTask taskThread = new BaseTask(this, reqVo, handler);
		record.add(taskThread);
		this.threadPoolManager.addTask(taskThread);
	}

	/**
	 * 
	 */
	protected abstract void loadViewLayout();;

	/**
	 * 
	 */
	protected abstract void findViewById();

	/**
	 * 
	 */
	protected abstract void setListener();

	/**
	 * 
	 */
	protected abstract void processLogic();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		application.removeActvity(this);
		// record.clear();
		record = null;
		context = null;
		threadPoolManager = null;
		inflate = null;
		application = null;
	}

	protected void toast(String str) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();

	}
}
