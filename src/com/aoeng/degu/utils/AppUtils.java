package com.aoeng.degu.utils;

import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.UIUtils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class AppUtils {

	/**
	 * 在AndroidManifest.xml中，<meta-data>元素可以作为子元素，被包含在<activity>、<application>
	 * 、<service>和<receiver>元素中，但 不同的父元素，在应用时读取的方法也不同。
	 * 
	 * 1 ：在Activity的应用。 xml代码段： <activity...> <meta-data android:name="myMsg"
	 * android:value="hello my activity"></meta-data> </activity>
	 * 
	 * java代码段： ActivityInfo info=this.getPackageManager()
	 * .getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
	 * String msg=info.metaData.getString("myMsg");
	 * System.out.println("myMsg:"+msg);
	 * 
	 * 2：在application的应用。 xml代码段： <application...> <meta-data
	 * android:value="hello my application" android:name="myMsg"></meta-data>
	 * </application>
	 * 
	 * java代码段： ApplicationInfo appInfo = this.getPackageManager()
	 * .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
	 * String msg=appInfo.metaData.getString("myMsg");
	 * System.out.println("myMsg:"+msg);
	 * 
	 * 3：在service的应用。 xml代码段： <service android:name="MetaDataService">
	 * <meta-data android:value="hello my service"
	 * android:name="myMsg"></meta-data> </service>
	 * 
	 * java代码段： ComponentName cn=new ComponentName(this, MetaDataService.class);
	 * ServiceInfo info=this.getPackageManager() .getServiceInfo(cn,
	 * PackageManager.GET_META_DATA); String
	 * msg=info.metaData.getString("myMsg"); System.out.println("myMsg:"+msg);
	 * 
	 * 4: 在receiver的应用。 xml代码段: <receiver android:name="MetaDataReceiver">
	 * <meta-data android:value="hello my receiver"
	 * android:name="myMsg"></meta-data> <intent-filter> <action
	 * android:name="android.intent.action.PHONE_STATE"></action>
	 * </intent-filter> </receiver> java代码段： ComponentName cn=new
	 * ComponentName(context, MetaDataReceiver.class); ActivityInfo
	 * info=context.getPackageManager() .getReceiverInfo(cn,
	 * PackageManager.GET_META_DATA); String
	 * msg=info.metaData.getString("myMsg"); System.out.println("myMsg:"+msg);
	 */

	private static final String KEY_APP_KEY = "USER_PRIVATE_KEY";

	// 取得AppKey
	public static String getAppKey(Context context) {
		Bundle metaData = null;
		String appKey = null;
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			if (null != ai)
				metaData = ai.metaData;
			if (null != metaData) {
				appKey = metaData.getString(KEY_APP_KEY);
			}
		} catch (NameNotFoundException e) {

		}
		return appKey;
	}

	public static String getAppName() {
		// TODO Auto-generated method stub
		ApplicationInfo info = UIUtils.getContext().getApplicationContext().getApplicationInfo();
		String pache = info.packageName;
		String name = info.name;
		LogUtils.i(pache + " 	" + name);
		return name;
	}
}
