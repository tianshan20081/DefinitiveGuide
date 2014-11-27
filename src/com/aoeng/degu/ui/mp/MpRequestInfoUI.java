package com.aoeng.degu.ui.mp;

import android.view.View;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.StringUtils;

public class MpRequestInfoUI extends BaseUI {

	private TextView tvInfo;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_mp_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		tvInfo = (TextView) findView(R.id.tvInfo);

		String extras = getIntent().getStringExtra("extras");
		if (!StringUtils.isEmpty(extras)) {
			tvInfo.setText(extras);
		}
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
