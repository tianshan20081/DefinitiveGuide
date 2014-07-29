/**
 * 
 */
package com.aoeng.degu.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Jul 29, 2014 3:02:11 PM
 * 
 */
public class MD5Utils {

	/**
	 * @param md5Src
	 * @return
	 */
	public static String getJavaMD5(String md5Src) {
		// TODO Auto-generated method stub
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(md5Src.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// result = buf.toString().substring(8, 24);
			// System.out.println("mdt 16bit: " + buf.toString().substring(8, 24));
			// System.out.println("md5 32bit: " + buf.toString());
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] args) {
		System.out.println("md5 zhaiyao---->" + getJavaMD5("test"));
	}
}
