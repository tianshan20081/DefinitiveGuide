/**
 * 
 */
package com.aoeng.degu.domain;

import android.graphics.drawable.Drawable;

/**
 * May 19, 2014 10:10:08 AM
 * 
 */
public class AppInfo {

	private String name;
	private String packageName;
	private String label;
	private Drawable icon;

	/**
	 * 
	 */
	public AppInfo() {
		super();
	}

	/**
	 * @param name
	 * @param packageName
	 * @param label
	 * @param icon
	 * @param logo
	 */
	public AppInfo(String name, String packageName, String label, Drawable icon) {
		super();
		this.name = name;
		this.packageName = packageName;
		this.label = label;
		this.icon = icon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the icon
	 */
	public Drawable getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}


}
