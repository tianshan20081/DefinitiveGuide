package com.aoeng.degu.ui.bl;

import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.LogUtils;

public class BleSyUserUI extends BaseUI {

	private BleSyUserUI mSyUser;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub

		setContentView(R.layout.ui_ble_sy_user);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

		if (null == getIntent()) {
			finish();
			return;
		}
		mSyUser = (BleSyUserUI) getIntent().getSerializableExtra("blesyuser");
		if (null == mSyUser) {
			finish();
			return;
		}
		LogUtils.e(mSyUser.toString());
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
