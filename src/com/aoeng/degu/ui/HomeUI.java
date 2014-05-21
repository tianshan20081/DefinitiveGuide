package com.aoeng.degu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.receiver.ReceiverUI;
import com.aoeng.degu.ui.apps.AppManagerUI;
import com.aoeng.degu.ui.cd.CoordinatesUI;
import com.aoeng.degu.ui.cp.ContentProviderUI;
import com.aoeng.degu.ui.imgs.ImageViewsUI;
import com.aoeng.degu.ui.jni.JNIHomeUI;
import com.aoeng.degu.ui.logins.LoginUIs;
import com.aoeng.degu.ui.lvs.ListViewsUI;
import com.aoeng.degu.ui.nt.NetWorkUI;
import com.aoeng.degu.ui.security.SecurityUI;
import com.aoeng.degu.ui.services.ServiceUI;
import com.aoeng.degu.ui.uis.GroupMainUI;
import com.aoeng.degu.ui.uis.UIsUI;
import com.aoeng.degu.ui.wv.WebViewUI;

public class HomeUI extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_ui);

		this.findViewById(R.id.chapter1).setOnClickListener(this);
		this.findViewById(R.id.chapter2).setOnClickListener(this);
		this.findViewById(R.id.widget).setOnClickListener(this);
		this.findViewById(R.id.dataSave).setOnClickListener(this);
		this.findViewById(R.id.btnSystemUI).setOnClickListener(this);
		this.findViewById(R.id.btnUserUI).setOnClickListener(this);
		this.findViewById(R.id.btnGroupUI).setOnClickListener(this);
		this.findViewById(R.id.btnReceiver).setOnClickListener(this);
		this.findViewById(R.id.btnContentProvider).setOnClickListener(this);
		this.findViewById(R.id.btnServices).setOnClickListener(this);
		this.findViewById(R.id.btnNetWork).setOnClickListener(this);
		this.findViewById(R.id.btnWebView).setOnClickListener(this);

		this.findViewById(R.id.btnJNI).setOnClickListener(this);
		this.findViewById(R.id.btnCoordinates).setOnClickListener(this);

		this.findViewById(R.id.btnCustomerUI).setOnClickListener(this);
		this.findViewById(R.id.btnListViews).setOnClickListener(this);

		this.findViewById(R.id.btnLoginUIs).setOnClickListener(this);
		this.findViewById(R.id.btnLocations).setOnClickListener(this);

		this.findViewById(R.id.btnImgs).setOnClickListener(this);
		this.findViewById(R.id.btnApp).setOnClickListener(this);
		
//		反编译与安全
		this.findViewById(R.id.btnSecurity).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnLocations:
			intent = new Intent(HomeUI.this, LocationsUI.class);
			break;
		case R.id.btnListViews:
			intent = new Intent(HomeUI.this, ListViewsUI.class);
			break;
		case R.id.btnCoordinates:
			intent = new Intent(this, CoordinatesUI.class);
			break;
		case R.id.btnJNI:
			intent = new Intent(this, JNIHomeUI.class);
			break;
		case R.id.btnWebView:
			intent = new Intent(this, WebViewUI.class);
			break;
		case R.id.btnNetWork:
			intent = new Intent(this, NetWorkUI.class);
			break;
		case R.id.btnServices:
			intent = new Intent(this, ServiceUI.class);
			startActivity(intent);
			break;
		case R.id.btnContentProvider:
			intent = new Intent(this, ContentProviderUI.class);
			break;
		case R.id.btnReceiver:
			intent = new Intent(this, ReceiverUI.class);
			break;
		case R.id.btnGroupUI:
			intent = new Intent(this, GroupMainUI.class);
			overridePendingTransition(R.anim.fade, R.anim.fade);
			break;
		case R.id.btnUserUI:
			// 当多个应用程序定义的 action 相同时，会出现选择框
			intent = new Intent("com.aoeng.degu.ui.uis.ACTION_UIS");
			break;
		case R.id.btnSystemUI:
			intent = new Intent(this, UIsUI.class);
			break;
		case R.id.chapter1:
			intent = new Intent(HomeUI.this, Chapter1UI.class);
			break;

		case R.id.chapter2:
			intent = new Intent(this, FrameUI.class);
			break;
		case R.id.widget:
			intent = new Intent(this, WidgetUI.class);
			break;
		case R.id.dataSave:
			intent = new Intent(this, DataSaveUI.class);
			break;
		case R.id.btnCustomerUI:
			// 自定义控件
			intent = new Intent(HomeUI.this, CustomerViewUI.class);

			break;
		case R.id.btnLoginUIs:
			intent = new Intent(HomeUI.this, LoginUIs.class);
			break;
		case R.id.btnImgs:
			intent = new Intent(HomeUI.this, ImageViewsUI.class);
			break;
		case R.id.btnApp:// 应用程序管理
			intent = new Intent(HomeUI.this, AppManagerUI.class);
			break;
		case R.id.btnSecurity:// 反编译与安全
			intent = new Intent(HomeUI.this, SecurityUI.class);
			break;
		}

		startActivity(intent);

	}

}
