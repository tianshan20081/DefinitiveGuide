/**
 * 
 */
package com.aoeng.degu.ui.security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * May 21, 2014 4:47:44 PM
 * 
 */
public class SecurityUI extends BaseUI {

	private Button btnAntidex2Jar;
	private Button btnProguard;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_security_home);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnAntidex2Jar = (Button) findView(R.id.btnAntidex2Jar);
		btnProguard = (Button) findView(R.id.btnProguard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnAntidex2Jar.setOnClickListener(this);
		btnProguard.setOnClickListener(this);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAntidex2Jar:
			startActivity(new Intent(this, Antidex2JarUI.class));
			break;
		case R.id.btnProguard:
			startActivity(new Intent(this, ProguardUI.class));
			break;

		default:
			break;
		}
	}

}
