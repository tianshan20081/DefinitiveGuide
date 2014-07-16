/**
 * 
 */
package com.aoeng.degu.utils;

/**
 * Jul 16, 2014 10:43:10 AM
 * 
 */
public class JniUtils {

	static {
		System.loadLibrary("defgui");
	}

	/**
	 * @return
	 */
	public native static String getMsgFromJni();

	/**
	 * @return
	 */
	public native static String getMsgFromJniCpp();

}
