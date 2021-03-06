package com.aoeng.degu.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.jpush.android.api.JPushInterface;

import com.aoeng.degu.R;
import com.aoeng.degu.receiver.ReceiverUI;
import com.aoeng.degu.ui.apps.AppManagerUI;
import com.aoeng.degu.ui.bl.BlHomeUI;
import com.aoeng.degu.ui.cd.CoordinatesUI;
import com.aoeng.degu.ui.chartengine.ChartEngineHomeUI;
import com.aoeng.degu.ui.cp.ContentProviderUI;
import com.aoeng.degu.ui.encryption.EncHomeUI;
import com.aoeng.degu.ui.eventdispatch.EventDispatchUI;
import com.aoeng.degu.ui.games.GameHomeUI;
import com.aoeng.degu.ui.handler.ThreadHandlerUI;
import com.aoeng.degu.ui.imgs.ImageViewsHomeUI;
import com.aoeng.degu.ui.internet.InternetHomeUI;
import com.aoeng.degu.ui.jni.JNIHomeUI;
import com.aoeng.degu.ui.logins.LoginUIs;
import com.aoeng.degu.ui.lvs.ListViewsUI;
import com.aoeng.degu.ui.media.MediaHomeUI;
import com.aoeng.degu.ui.mm.MobileManagerHomeUI;
import com.aoeng.degu.ui.nt.NetWorkUI;
import com.aoeng.degu.ui.phonegap.PhoneGapHomeUI;
import com.aoeng.degu.ui.security.SecurityUI;
import com.aoeng.degu.ui.services.ServiceUI;
import com.aoeng.degu.ui.shell.ShellHomeUI;
import com.aoeng.degu.ui.uis.GroupMainUI;
import com.aoeng.degu.ui.uis.UIsUI;
import com.aoeng.degu.ui.views.ViewsUI;
import com.aoeng.degu.ui.wv.WebViewUI;
import com.aoeng.degu.utils.common.UIUtils;

public class HomeUI extends BaseUI implements OnClickListener {
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnChartEngine:
			intent = new Intent(UIUtils.getContext(), ChartEngineHomeUI.class);
			break;
		case R.id.btnPhoneGap:
			intent = new Intent(UIUtils.getContext(), PhoneGapHomeUI.class);
			break;
		case R.id.btnEventDistribution:
			intent = new Intent(UIUtils.getContext(), EventDispatchUI.class);
			break;
		case R.id.btnHandler:
			intent = new Intent(getApplicationContext(), ThreadHandlerUI.class);
			break;
		case R.id.btnEnc:
			intent = new Intent(HomeUI.this, EncHomeUI.class);
			break;
		case R.id.btnInternet:
			intent = new Intent(this, InternetHomeUI.class);
			break;
		case R.id.btnMedia:
			intent = new Intent(this, MediaHomeUI.class);
			break;
		case R.id.btnGames:
			intent = new Intent(this, GameHomeUI.class);
			break;
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
			intent = new Intent(HomeUI.this, ImageViewsHomeUI.class);
			break;
		case R.id.btnApp:// 应用程序管理
			intent = new Intent(HomeUI.this, AppManagerUI.class);
			break;
		case R.id.btnSecurity:// 反编译与安全
			intent = new Intent(HomeUI.this, SecurityUI.class);
			break;
		case R.id.btnViews://
			intent = new Intent(HomeUI.this, ViewsUI.class);
			break;
		case R.id.btnMobileManager:// 手机管理
			intent = new Intent(HomeUI.this, MobileManagerHomeUI.class);
			break;
		case R.id.btnBleHome:// 手机管理
			intent = new Intent(HomeUI.this, BlHomeUI.class);
			break;
		case R.id.btnShell:// run shell command
			intent = new Intent(HomeUI.this, ShellHomeUI.class);
			break;
		case R.id.btnAnim:
			break;
		}
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
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
		// 反编译与安全
		this.findViewById(R.id.btnSecurity).setOnClickListener(this);
		this.findViewById(R.id.btnGames).setOnClickListener(this);
		this.findViewById(R.id.btnMedia).setOnClickListener(this);
		this.findViewById(R.id.btnInternet).setOnClickListener(this);
		this.findViewById(R.id.btnViews).setOnClickListener(this);
		this.findViewById(R.id.btnEnc).setOnClickListener(this);
		this.findViewById(R.id.btnHandler).setOnClickListener(this);
		this.findViewById(R.id.btnEventDistribution).setOnClickListener(this);
		this.findViewById(R.id.btnPhoneGap).setOnClickListener(this);
		this.findViewById(R.id.btnChartEngine).setOnClickListener(this);
		this.findViewById(R.id.btnMobileManager).setOnClickListener(this);
		this.findViewById(R.id.btnBleHome).setOnClickListener(this);
		this.findViewById(R.id.btnShell).setOnClickListener(this);
		this.findViewById(R.id.btnAnim).setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
	}
}
