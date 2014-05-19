/**
 * 
 */
package com.aoeng.degu.ui.imgs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aoeng.degu.R;

/**
 * May 16, 2014 6:06:48 PM 圓形圖片
 */
public class CircleImgUI extends Activity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_imgs_circle);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new MainFragment())
					.commit();
		}
	}

	@SuppressLint("NewApi")
	public static class MainFragment extends Fragment {

		public MainFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.ui_imgs_circle_fragment_main, container,
					false);
			return rootView;
		}
	}
}
