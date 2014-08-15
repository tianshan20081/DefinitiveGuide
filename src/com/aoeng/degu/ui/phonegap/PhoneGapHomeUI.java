package com.aoeng.degu.ui.phonegap;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.UIUtils;

public class PhoneGapHomeUI extends BaseUI {

	private Button btnSimple;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSimple:
			UIUtils.startActivity(new Intent(UIUtils.getContext(), SimplePhoneGapUI.class));
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_phonegap_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnSimple = (Button) findView(R.id.btnSimple);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnSimple.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
