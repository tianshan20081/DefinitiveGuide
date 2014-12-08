/**
 * 
 */
package com.aoeng.degu.ui.media;

import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * @author sczhang 2014年12月8日 下午3:54:53
 * @Email {zhangshch0131@126.com}
 */
public class BenchmarkUI extends BaseUI {
	private View runBenchmarkButton;
	private TextView textView;
	private AsyncTask<Object, Object, String> benchmarkTask;

	private final View.OnClickListener runBenchmark = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (benchmarkTask == null) {
				String path = Environment.getExternalStorageDirectory().getPath() + "/zxingbenchmark";
				benchmarkTask = new BenchmarkAsyncTask(BenchmarkUI.this, path);
				runBenchmarkButton.setEnabled(false);
				textView.setText(R.string.benchmark_running);
				benchmarkTask.execute(AsyncTask.THREAD_POOL_EXECUTOR);
			}
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_media_benchmark);
		runBenchmarkButton = findViewById(R.id.benchmark_run);
		runBenchmarkButton.setOnClickListener(runBenchmark);
		textView = (TextView) findViewById(R.id.benchmark_help);
		benchmarkTask = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

	void onBenchmarkDone(String message) {
		textView.setText(message + "\n\n" + getString(R.string.benchmark_help));
		runBenchmarkButton.setEnabled(true);
		benchmarkTask = null;
	}
}
