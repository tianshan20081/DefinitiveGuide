package com.aoeng.degu.ui.lvs;

import com.aoeng.degu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ListViewsUI extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ui_lvs_home);
		
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		this.findViewById(R.id.lvNormal).setOnClickListener(this);
		this.findViewById(R.id.lvPages).setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null ;
		switch (v.getId()) {
		case R.id.lvPages:
			intent = new Intent(ListViewsUI.this, PagesListViewUI.class);
			break;
		case R.id.lvNormal:
			intent = new Intent(ListViewsUI.this, NormalListViewUI.class);
			break;
		}
		startActivity(intent);
	}
}
