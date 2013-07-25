package com.aoeng.degu.receiver;

import com.aoeng.degu.ui.HomeUI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartUpReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent homeIntent = new Intent(context, HomeUI.class);
		homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(homeIntent);
	}

}
