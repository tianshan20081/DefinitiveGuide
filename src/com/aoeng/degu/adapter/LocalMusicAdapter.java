/**
 * 
 */
package com.aoeng.degu.adapter;

import java.util.List;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.Music;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author sczhang 2015年1月30日 下午3:28:07
 */
public class LocalMusicAdapter extends BaseAdapter {

	private List<Music> datas;

	/**
	 * @param result
	 */
	public LocalMusicAdapter(List<Music> result) {
		// TODO Auto-generated constructor stub
		this.datas = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.datas.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.datas.get(position);
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
		ViewHolder holder;
		if (null != convertView) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			holder = new ViewHolder();
			convertView = UIUtils.inflate(R.layout.ui_imgs_autogroup_mu_item);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvPath = (TextView) convertView.findViewById(R.id.tvPath);
			holder.tvInfo = (TextView) convertView.findViewById(R.id.tvInfo);
			convertView.setTag(holder);
		}
		Music mi = (Music) getItem(position);
		if (null != mi) {
			holder.tvName.setText(mi.getTitle());
			holder.tvInfo.setText(mi.getDuration() + StringUtils.formatFileSize(mi.getSize()));
			holder.tvPath.setText(mi.getPath());
		}
		return convertView;
	}

	private static class ViewHolder {
		public static TextView tvName;
		public static TextView tvPath;
		public static TextView tvInfo;

	}
}
