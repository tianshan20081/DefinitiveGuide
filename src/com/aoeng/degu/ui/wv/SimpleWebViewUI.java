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
		
		
		//添加对  JavaScript 的支持
		wvSimple.setJavaScriptEnabled(true);
		//添加 放大缩小控件
		wvSimple.setBuiltInZoomControls(true);
		wvSimple.setSupportZoom(true);
		wvSimple.setDefaultZoom(ZoomDensity.CLOSE);
		//初始时候页面总宽度为 手机宽度。
		wvSimple.setUseWideViewPort(true);
		wvSimple.setLoadWithOverviewMode(true);
		
		if(URLUtil.isHttpsUrl(url)){
			//请求链接 为 https 访问时
			wvSimple.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				handler.proceed();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				//当页面加载完毕的时候 加载进度条消失
				ViewUtils.dismissDia(dialog);
			}
		});
		}else if(URLUtil.isHttpUrl(url)){
			//请求访问为HTTP 访问
			
		}
		
		if (URLUtil.isNetworkUrl(url)) {
			wvSimple.loadUrl(url);
		}

	}

}
