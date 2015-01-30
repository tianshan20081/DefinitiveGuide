/**
 * 
 */
package com.aoeng.degu.utils;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.aoeng.degu.domain.ImageGroup;
import com.aoeng.degu.domain.Music;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * @author sczhang 2015年1月30日 上午11:12:35
 */
public class MediaUtils {

	/**
	 * @return
	 */
	public static List<Music> getAllMp3s() {
		// TODO Auto-generated method stub
		long startScan = System.currentTimeMillis();
		LogUtils.i("start scan " + startScan);
		List<Music> musics = null;
		// 获取sdcard的路径：外置和内置
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String[] projection = { MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DURATION, // 音频文件播放时长
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA, // 音频文件路径
				MediaStore.Audio.Media.SIZE };
		StringBuffer selection = new StringBuffer();
		selection.append(MediaStore.Audio.Media.SIZE).append(" > ? ");
		String selectionArgs[] = { (1024 * 1024 * 2) + "" };
		Cursor cursor = UIUtils.getContext().getContentResolver().query(uri, projection, selection.toString(), selectionArgs, null);
		if (null == cursor) {
			LogUtils.e("scan failure");
		} else if (!cursor.moveToFirst()) {
			LogUtils.e("没有 音频文件");
		} else {
			musics = new ArrayList<Music>();
			do {
				Music m = new Music();
				m.setTitle(cursor.getString(0));
				m.setDuration(cursor.getString(1));
				m.setArtist(cursor.getString(2));
				m.setId(cursor.getString(3));
				m.setPath(cursor.getString(4));
				m.setSize(cursor.getLong(5));
				musics.add(m);
			} while (cursor.moveToNext());
		}
		return musics;

	}
}
