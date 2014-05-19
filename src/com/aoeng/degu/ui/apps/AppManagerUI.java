/**
 * 
 */
package com.aoeng.degu.ui.apps;

import com.aoeng.degu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * May 19, 2014  9:50:02 AM
 * 應用程序管理界面
 */
public class AppManagerUI extends Activity implements OnClickListener {
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_apps_manager_home);
		
		this.findViewById(R.id.btnAllApps).setOnClickListener(this);
		this.findViewById(R.id.btnSysApps).setOnClickListener(this);
		this.findViewById(R.id.btnAppSize).setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAllApps:
			startActivity(new Intent(this, AllAppUI.class));
			break;
		case R.id.btnSysApps:
			startActivity(new Intent(this, SysAppsUI.class));
			break;
		case R.id.btnAppSize:
			startActivity(new Intent(this, AppSizeUI.class));
			break;

		default:
			break;
		}
	}

}
