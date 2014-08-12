package com.aoeng.degu.ui.wv;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

public class JsCallJavaUI extends BaseUI {

	private WebView wvJsCallJava;
	private Button btnJavaCallJs;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnJavaCallJs:
			// 无参数调用
			wvJsCallJava.loadUrl("javascript:javacalljs()");
			// 传递参数调用
			wvJsCallJava.loadUrl("javascript:javacalljswithargs(" + "'hello world'" + ")");
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_wv_jscalljava);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		wvJsCallJava = (WebView) findViewById(R.id.wvJsCallJava);
		btnJavaCallJs = (Button) findView(R.id.btnJavaCallJs);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnJavaCallJs.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		wvJsCallJava.getSettings().setJavaScriptEnabled(true);
		wvJsCallJava.loadUrl("file:///android_asset/jscalljava.html");

		wvJsCallJava.addJavascriptInterface(this, "wst");
	}

	public void startFunction() {
		Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// msgView.setText(msgView.getText() + "\njs调用了java函数");

			}
		});
	}

	public void startFunction(final String str) {
		Toast.makeText(this, "\njs调用了java函数传递参数：" + str, Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// msgView.setText(msgView.getText() + "\njs调用了java函数传递参数：" +
				// str);

			}
		});
	}
}
