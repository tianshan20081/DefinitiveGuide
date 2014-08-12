package com.aoeng.degu.ui.wv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.UIUtils;

public class WebViewUI extends BaseUI implements OnClickListener {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnSimpleWebView:
			intent = new Intent(this, SimpleWebViewUI.class);
			startActivity(intent);
			break;
		case R.id.btnWvJsCallJava:
			UIUtils.startActivity(new Intent(UIUtils.getContext(), JsCallJavaUI.class));
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_wv_home);

		

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		this.findViewById(R.id.btnSimpleWebView).setOnClickListener(this);
		this.findViewById(R.id.btnWvJsCallJava).setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		
	}

}
