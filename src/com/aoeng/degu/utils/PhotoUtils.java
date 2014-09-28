package com.aoeng.degu.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.aoeng.degu.domain.ImageGroup;
import com.aoeng.degu.utils.common.UIUtils;

public class PhotoUtils {

	public static TreeMap<String, ImageGroup> getAllPhotos() {
		// TODO Auto-generated method stub
		TreeMap<String, ImageGroup> treeMap = new TreeMap<String, ImageGroup>(new Comparator<String>() {

			@Override
			public int compare(String lhs, String rhs) {
				// TODO Auto-generated method stub
				return (int) (Long.parseLong(rhs) - Long.parseLong(lhs));
			}
		});

		HashMap<String, String> paramMap = new HashMap<String, String>();
		String[] arrayOfString = { "_id", "_data", "_size", "_display_name", "mime_type", "title", "date_added", "date_modified", "description", "isprivate",
				"latitude", "longitude", "datetaken", "orientation", "mini_thumb_magic", "bucket_id", "bucket_display_name" };
		ContentResolver localContentResolver = UIUtils.getContext().getContentResolver();
		for (Uri localUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;; localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI) {
			Cursor localCursor = localContentResolver.query(localUri, arrayOfString, "_size > 32768", null, "datetaken DESC");
			if (localCursor.moveToFirst()) {
				int i = localCursor.getColumnIndexOrThrow("bucket_id");
				int j = localCursor.getColumnIndexOrThrow("bucket_display_name");
				do {
					String str1 = localCursor.getString(i);
					String str2 = localCursor.getString(j);
					if ((!TextUtils.isEmpty(str2)) && (!paramMap.containsKey(str1)))
						paramMap.put(str1, str2);
					Log.d("preference", str1 + " " + str2);
				} while (localCursor.moveToNext());
			}
		}

	}

	private static void a(Context paramContext, Map paramMap, boolean paramBoolean) {
		String[] arrayOfString = { "_id", "_data", "_size", "_display_name", "mime_type", "title", "date_added", "date_modified", "description", "isprivate",
				"latitude", "longitude", "datetaken", "orientation", "mini_thumb_magic", "bucket_id", "bucket_display_name" };
		ContentResolver localContentResolver = UIUtils.getContext().getContentResolver();
		if (paramBoolean)
			;
		for (Uri localUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;; localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI) {
			Cursor localCursor = localContentResolver.query(localUri, arrayOfString, "_size > 32768", null, "datetaken DESC");
			if (localCursor.moveToFirst()) {
				int i = localCursor.getColumnIndexOrThrow("bucket_id");
				int j = localCursor.getColumnIndexOrThrow("bucket_display_name");
				do {
					String str1 = localCursor.getString(i);
					String str2 = localCursor.getString(j);
					if ((!TextUtils.isEmpty(str2)) && (!paramMap.containsKey(str1)))
						paramMap.put(str1, str2);
					Log.d("preference", str1 + " " + str2);
				} while (localCursor.moveToNext());
			}
			return;
		}
	}
}
