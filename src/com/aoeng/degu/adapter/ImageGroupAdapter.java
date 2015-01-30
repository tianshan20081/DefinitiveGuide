/**
 * 
 */
package com.aoeng.degu.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.ImageGroup;
import com.aoeng.degu.utils.common.DateUtil;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * @author sczhang 2015年1月30日 下午4:11:46
 */
public class ImageGroupAdapter extends BaseAdapter {
	private List<ImageGroup> datas;

	/**
	 * @param result
	 */
	public ImageGroupAdapter(List<ImageGroup> result) {
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
	public ImageGroup getItem(int position) {
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
		ImageGroup ig = getItem(position);
		if (null != ig) {
			holder.tvName.setText(DateUtil.yyyyMMdd.format(ig.getMaxDate()) + "\t\t" + ig.getAlbumSize());
			// holder.tvInfo.setText(mi.getDuration() +
			// StringUtils.formatFileSize(mi.getSize()));
			holder.tvPath.setText("Location Latitude : " + ig.getLatitude() + "\t\t Longitude : " + ig.getLongitude());
		}
		return convertView;
	}

	private static class ViewHolder {
		public static TextView tvName;
		public static TextView tvPath;
		public static TextView tvInfo;

	}
}
