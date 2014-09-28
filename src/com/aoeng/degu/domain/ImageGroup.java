package com.aoeng.degu.domain;

import java.util.ArrayList;
import java.util.Date;

import com.aoeng.degu.utils.Constant;
import com.aoeng.degu.utils.DateUtils;
import com.aoeng.degu.utils.common.StringUtils;

public class ImageGroup {
	/**
	 * 编号
	 */
	private long groupId;
	/**
	 * 拍摄最晚日期
	 */
	private Date maxDate;
	/**
	 * 最早拍摄时间
	 */
	private Date minDate;
	/**
	 * 拍摄地理位置
	 */
	private String loaction;
	/**
	 * 拍照维度
	 */
	private Double latitude;
	/**
	 * 拍照经度
	 */
	private Double longitude;
	private String frontImgPath;
	private ArrayList<ImageInfo> infos = new ArrayList<ImageInfo>();

	public void add(ImageInfo info) {
		if (null == info) {
			return;
		}

		if (null == this.minDate || minDate.getTime() > info.getTakenDate().getTime()) {
			this.minDate = info.getTakenDate();
		}
		if (null == this.maxDate || maxDate.getTime() < info.getTakenDate().getTime()) {
			this.maxDate = info.getTakenDate();
		}
		if (null == this.latitude || 0.0 == this.latitude || null == this.longitude || 0.0 == this.longitude) {
			this.latitude = info.getLatitude();
			this.longitude = info.getLongitude();
		}
		if (StringUtils.isEmpty(this.frontImgPath)) {
			this.frontImgPath = info.getPicPath();
		}
		if (0 == this.groupId || this.groupId < info.getTakenDate().getTime()) {
			this.groupId = info.getTakenDate().getTime();
		}
		infos.add(info);
	}

	public long getGroupId() {
		return groupId;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public Date getMinDate() {
		return minDate;
	}

	public String getLoaction() {
		return loaction;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public ArrayList<ImageInfo> getInfos() {
		return infos;
	}

	public String getFrontImgPath() {
		return frontImgPath;
	}

	/**
	 * Image 属于 该 组 属于 就添加，不属于 就返回 false
	 * 
	 * @param info
	 * @return
	 */
	public boolean isBelong(ImageInfo info) {
		// TODO Auto-generated method stub
		if (info.getTakenDate().getTime() < this.maxDate.getTime() + Constant.GroupMaxMinutes
				&& info.getTakenDate().getTime() > this.minDate.getTime() - Constant.GroupMaxMinutes) {
			// LogUtils.i("isBelong--------------true");
			return true;
		}
		// LogUtils.i("isBelong--------------false");
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		this.groupId = 0;
		this.infos.clear();
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.maxDate = null;
		this.minDate = null;
		this.loaction = "";
	}

	@Override
	public String toString() {
		return "ImageGroup [groupId=" + groupId + ", maxDate=" + maxDate + ", minDate=" + minDate + ", loaction=" + loaction + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", frontImgPath=" + frontImgPath + ", infos.size()=" + infos.size() + "]";
	}

	public String toStr() {
		return "ImageGroup [groupId=" + groupId + ", maxDate=" + DateUtils.yyyyMMdd_HHmmss.format(maxDate) + ", minDate="
				+ DateUtils.yyyyMMdd_HHmmss.format(minDate) + ", frontImgPath=" + frontImgPath + ", infos.size()=" + infos.size() + "]";
	}

}
