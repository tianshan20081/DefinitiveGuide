package com.aoeng.degu.utils;

import java.io.File;

import android.os.Environment;
import android.text.TextUtils;

public class FileUtils {
	private static String ROOT_DIR = "df";
	private static String ROOT_CRASH = "crash";
	private static String ROOT_IMG = "img";

	public static String getAppCrashPath() {
		// TODO Auto-generated method stub
		String appPath = getAppRootPath();
		if (!TextUtils.isEmpty(appPath)) {
			return appPath.concat(File.separator).concat(ROOT_CRASH);
		}
		return null;
	}

	private static String getAppRootPath() {
		// TODO Auto-generated method stub
		if (isSDCardAvailiable()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath()
					.concat(File.separator).concat(ROOT_DIR);
		}
		return null;
	}

	private static boolean isSDCardAvailiable() {
		// TODO Auto-generated method stub
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

}
