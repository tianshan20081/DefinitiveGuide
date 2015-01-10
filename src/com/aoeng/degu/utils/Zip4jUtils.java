/**
 * 
 */
package com.aoeng.degu.utils;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import com.aoeng.degu.utils.common.StringUtils;

/**
 * @author sczhang 2015年1月9日 上午9:38:19
 */
public class Zip4jUtils {

	public static void zipFile(String srcPath, String destPath, String pwd) {
		File srcFile = new File(srcPath);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		if (!StringUtils.isEmpty(pwd)) {
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			parameters.setPassword(pwd);
		}
		try {
			ZipFile zipFile = new ZipFile(destPath);
			if (srcFile.isDirectory()) {
				zipFile.addFolder(srcFile, parameters);
			} else {
				zipFile.addFile(srcFile, parameters);
			}
			System.out.println("commpress ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @param srcFile
	 * @param destFile
	 */
	private static void zipFile(String srcFile, String destFile) {
		// TODO Auto-generated method stub
		zipFile(srcFile, destFile, null);
	}
}
