/**
 * 
 */
package com.aoeng.degu.ui.jni;

import android.view.View;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.JNIUtils;

/**
 * @author [Aoeng Zhang] @datatime Jul 26, 2013:2:34:42 PM
 * @Email [zhangshch2008@gmail.com] Jul 26, 2013
 */
public class JNIHomeUI extends BaseUI {

	private Button btnHello;
	private Button btnSum;
	private Button btnSumsCpp;
	private Button btnArraySort;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnHello:
			toast(JNIUtils.msgFromJNI());
			break;
		case R.id.btnSum:
			toast(JNIUtils.getSum(1, 2) + "");
			break;
		case R.id.btnSumsCpp:
			toast(JNIUtils.getFactorial(5) + "");
			break;
		case R.id.btnArraySort:
			int[] its = { 3, 5, 7, 9, 1, 2, 7 };
			int[] sorts = JNIUtils.getArraySort(its);

			toast(sorts.toString());
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_jni_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnHello = (Button) findView(R.id.btnHello);
		btnSum = (Button) findView(R.id.btnSum);
		btnSumsCpp = (Button) findView(R.id.btnSumsCpp);
		btnArraySort = (Button) findView(R.id.btnArraySort);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnHello.setOnClickListener(this);
		btnSum.setOnClickListener(this);
		btnSumsCpp.setOnClickListener(this);
		btnArraySort.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
