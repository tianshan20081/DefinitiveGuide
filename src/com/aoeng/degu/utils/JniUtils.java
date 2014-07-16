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

	/**
	 * @param i
	 * @return
	 */
	public native static int getJieCheng(int i);

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	public native static int getSum(int i, int j);

}
