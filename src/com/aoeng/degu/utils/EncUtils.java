/**
 * 
 */
package com.aoeng.degu.utils;

import com.aoeng.degu.utils.md5.MD5Utils;

/**
 * Jul 29, 2014 3:01:09 PM
 * 
 */
public class EncUtils {

	/**
	 * @param md5Src
	 * @return
	 */
	public static String getJavaMD5(String md5Src) {
		// TODO Auto-generated method stub

		return MD5Utils.getJavaMD5(md5Src);
	}

	/**
	 * @param md5CppSrc
	 * @return
	 */
	public static String getCppMD5(String md5CppSrc) {
		// TODO Auto-generated method stub
		return JniUtils.getCppMD5(md5CppSrc);
	}

}
