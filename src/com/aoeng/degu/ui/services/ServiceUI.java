package com.aoeng.degu.ui.services;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.services.BaseServices;
import com.aoeng.degu.services.BindServices;
import com.aoeng.degu.utils.ViewUtils;

public class ServiceUI extends Activity implements OnClickListener {
	private Intent intent;
	private BindServices bindServices;
	private ServiceConnection serviceConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_service_home);

		this.findViewById(R.id.btnStartService).setOnClickListener(this);
		this.findViewById(R.id.btnStopService).setOnClickListener(this);
		this.findViewById(R.id.btnBindService).setOnClickListener(this);
		this.findViewById(R.id.btnUnBindService).setOnClickListener(this);
		this.findViewById(R.id.btnServices).setOnClickListener(this);
		this.findViewById(R.id.btnClearServices).setOnClickListener(this);

		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				bindServices = null;
				ViewUtils.toastCenter(ServiceUI.this, "Services Faild", false);
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				bindServices = ((BindServices.MyBinder) service).getServices();
				ViewUtils.toastCenter(ServiceUI.this, "Services Connection !", false);
			}
		};
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnServices:
			ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningServiceInfo> serviceInfos = activityManager.getRunningServices(100);
			String str = "";
			for (RunningServiceInfo runningServiceInfo : serviceInfos) {
				str += runningServiceInfo.service.getClassName() + "\n";
			}
			ViewUtils.log("Running Services:", str);
			ViewUtils.toast(this, str, true);
			break;
		case R.id.btnClearServices:

			break;
		case R.id.btnBindService:
			intent = new Intent(this, BindServices.class);
			bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.btnUnBindService:
			unbindService(serviceConnection);
			break;

		case R.id.btnStartService:
			intent = new Intent(this, BaseServices.class);
			startService(intent);
			break;
		case R.id.btnStopService:
			stopService(intent);
			break;

		default:
			break;
		}
	}

}
