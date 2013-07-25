package com.aoeng.degu.ui.wv;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.aoeng.degu.R;

public class SimpleWebViewUI extends Activity {
	private WebView wvSimple;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_wv_simplewv);

		String url = "http://www.beijing.com.cn/";
		wvSimple = (WebView) this.findViewById(R.id.wvSimple);
		if (URLUtil.isNetworkUrl(url)) {
			wvSimple.loadUrl(url);
		}

	}

}
