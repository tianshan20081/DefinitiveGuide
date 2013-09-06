/**
 * 
 */
package com.aoeng.degu.adapter;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.cv.TwoCacheImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * @author [ShaoCheng Zhang] Sep 5, 2013:2:50:54 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoWallTwoCacheAdapter extends BaseAdapter {
	private Context context;
	private String[] urls;
	private TwoCacheImageLoader imageLoader;

	public PhotoWallTwoCacheAdapter(Context context, String[] urls) {
		this.context = context;
		this.urls = urls;
		this.imageLoader = new TwoCacheImageLoader();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urls.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.ui_cv_pthoto2cache_item, null);
			imageView = (ImageView) convertView.findViewById(R.id.imPotoTwoCache);
			convertView.setTag(imageView);

		}else {
			
		}
		return null;
	}

}
