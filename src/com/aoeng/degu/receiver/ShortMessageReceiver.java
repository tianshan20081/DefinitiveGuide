package com.aoeng.degu.receiver;

import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.aoeng.degu.utils.common.Toaster;

public class ShortMessageReceiver extends BroadcastReceiver {
	private static final String TAG = ShortMessageReceiver.class.getName().toUpperCase();

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Toaster.toast(context, "注册为广播", true);
		Toaster.log(TAG, "注册为广播");
		Bundle bundle = intent.getExtras();
		if (null != bundle) {
			Set<String> set = bundle.keySet();
			for (String string : set) {
				Toaster.log(TAG, string);
			}
			Object[] objArray = (Object[]) bundle.get("pdus");
			SmsMessage[] smsMessages = new SmsMessage[objArray.length];
			for (int i = 0; i < objArray.length; i++) {
				smsMessages[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
				String s = "手机号：" + smsMessages[i].getOriginatingAddress() + "\n";
				s += "短信内容：" + smsMessages[i].getMessageBody();

				Toaster.toast(context, s.toString(), true);
				Toaster.log(TAG, s);
			}
		}

	}

}
