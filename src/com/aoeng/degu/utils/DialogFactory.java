package com.aoeng.degu.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aoeng.degu.R;

public class DialogFactory {
	public static Dialog creatRequestDialog(final Context context, String tip) {

		final Dialog dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.qq_dialog_layout);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		int width = Tools.getScreenWidth(context);
		lp.width = (int) (0.6 * width);

		TextView titleTxtv = (TextView) dialog.findViewById(R.id.tvLoad);
		if (tip == null || tip.length() == 0) {
			titleTxtv.setText("µÇÂŒÖÐ");
		} else {
			titleTxtv.setText(tip);
		}

		return dialog;
	}

}
