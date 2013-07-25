package com.aoeng.degu.ui.wv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;

public class WebViewUI extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_wv_home);

		this.findViewById(R.id.btnSimpleWebView).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnSimpleWebView:
			intent = new Intent(this, SimpleWebViewUI.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
