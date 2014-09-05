package com.aoeng.degu.ui.views;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.UIUtils;

public class DialogUI extends BaseUI {

	private ProgressBar dialog;

	protected final int UPDATE = 100;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATE:
				int pro = dialog.getProgress();
				dialog.setProgress(pro + 10);

				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_views_dialog);
		dialog = findView(R.id.pb);
		// dialog.setTitle("File is Downloading");
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while (i < 100) {
					SystemClock.sleep(1000);

					Message message = handler.obtainMessage();
					message.what = UPDATE;
					handler.sendMessage(message);
					i = i + 10;
				}

			}
		}).start();
		dialog.setMax(100);
		dialog.bringToFront();
		// dialog.
	}
}
