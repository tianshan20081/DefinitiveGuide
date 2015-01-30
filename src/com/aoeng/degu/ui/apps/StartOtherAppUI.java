/**
 * 
 */
package com.aoeng.degu.ui.apps;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * @author sczhang 2015年1月23日 上午10:34:52
 */
public class StartOtherAppUI extends BaseUI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// 新浪微博（编辑界面）：com.sina.weibo com.sina.weibo.EditActivity
		//
		// 腾讯微博（编辑界面）：com.tencent.WBlog
		// com.tencent.WBlog.activity.MicroblogInput
		//
		// 微信： com.tencent.mm com.tencent.mm.ui.LauncherUI
		//
		// QQ: com.tencent.mobileqq com.tencent.mobileqq.activity.HomeActivity
		ComponentName cmp = null;
		switch (v.getId()) {
		case R.id.btnOpenQq:
			cmp = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
			break;
		case R.id.btnOpenWeiBo:
			cmp = new ComponentName("com.sina.weibo", "com.sina.weibo.EditActivity");
			break;
		case R.id.btnOpenWeiChart:
			cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
			break;
		case R.id.btnOpenTenWeiBo:
			cmp = new ComponentName("com.tencent.WBlog", "com.tencent.WBlog.activity.MicroblogInput");
			break;
		case R.id.btnOpenNk:
			cmp = new ComponentName("com.nokee.mirror", "com.nokee.mirror.ui.login.LoginStep1");
			break;
		}
		startApp(cmp);
	}

	/**
	 * @param cmp
	 * 
	 */
	private void startApp(ComponentName cmp) {
		// TODO Auto-generated method stub
		if (null == cmp) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setComponent(cmp);

		startActivityForResult(intent, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_app_manager_other_app);
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
		findView(R.id.btnOpenQq).setOnClickListener(this);
		findView(R.id.btnOpenWeiBo).setOnClickListener(this);
		findView(R.id.btnOpenWeiChart).setOnClickListener(this);
		findView(R.id.btnOpenTenWeiBo).setOnClickListener(this);
		findView(R.id.btnOpenNk).setOnClickListener(this);
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

	private void start2() {
		// TODO Auto-generated method stub
		String packageName = "com.DeviceTest";
		Intent intent = new Intent();
		PackageManager packageManager = this.getPackageManager();
		intent = packageManager.getLaunchIntentForPackage(packageName);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
	}
}
