package com.aoeng.degu.ui.imgs;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.ImageInfo;
import com.aoeng.degu.ui.BaseUI;

public class PictureWallUI extends BaseUI {

	private ListView lvPicWall;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub

		setContentView(R.layout.ui_imgs_pic_wall);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		lvPicWall = (ListView) findView(R.id.lvPicWall);

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		log(Environment.DIRECTORY_DCIM);
		log(Environment.getRootDirectory().getAbsolutePath());
		log(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				File dicmFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
				HashMap<String, String> dicmMap = new HashMap<String, String>();
				if (null != dicmFile && dicmFile.isDirectory()) {
					File[] chFile = dicmFile.listFiles();
					if (null == chFile && chFile.length == 0) {
						log("empty Dicm");
						return;
					}
					for (File ch : chFile) {
						if (null != ch && ch.isFile()) {
							dicmMap.put(ch.getAbsolutePath(), new Date(ch.lastModified()).toString());
							Uri uri = Uri.parse(ch.getAbsolutePath());

							loadImgInfo(uri);

						}
					}
				}
				for (String info : dicmMap.keySet()) {
					StringBuffer sb = new StringBuffer();
					sb.append(info).append(":").append(dicmMap.get(info).toString());
					log(sb.toString());
				}

			}
		}).start();

	}

	protected void loadImgInfo(Uri uri) {
		// TODO Auto-generated method stub
		String[] filePathColumn = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE,
				MediaStore.Images.Media.SIZE, MediaStore.Images.Media.TITLE, MediaStore.Images.Media.DESCRIPTION, MediaStore.Images.Media.MIME_TYPE,
				MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.DATE_MODIFIED, MediaStore.Images.Media.DATE_TAKEN,
				MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.HEIGHT, MediaStore.Images.Media.WIDTH };

		Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
		ImageInfo picInfo = null;
		if (null == cursor) {
			log("null == cursor ");
			return;
		}
		while (cursor.moveToNext()) {
			picInfo = new ImageInfo();
			picInfo.setPicPath(cursor.getString(cursor.getColumnIndex(filePathColumn[0])));
			picInfo.setLatitude(cursor.getDouble(cursor.getColumnIndex(filePathColumn[1])));
			picInfo.setLongitude(cursor.getDouble(cursor.getColumnIndex(filePathColumn[2])));
			picInfo.setSize(cursor.getLong(cursor.getColumnIndex(filePathColumn[3])));
			picInfo.setTitle(cursor.getString(cursor.getColumnIndex(filePathColumn[4])));
			picInfo.setDescription(cursor.getString(cursor.getColumnIndex(filePathColumn[5])));
			picInfo.setMimeType(cursor.getString(cursor.getColumnIndex(filePathColumn[6])));
			picInfo.setAddDate(new Date((cursor.getLong(cursor.getColumnIndex(filePathColumn[7])))));
			picInfo.setModifyDate(new Date((cursor.getLong(cursor.getColumnIndex(filePathColumn[8])))));
			picInfo.setTakenDate(new Date((cursor.getLong(cursor.getColumnIndex(filePathColumn[9])))));
			picInfo.setDisplayName(cursor.getString(cursor.getColumnIndex(filePathColumn[10])));

			picInfo.setHeight(cursor.getInt(cursor.getColumnIndex(filePathColumn[11])));
			picInfo.setWidth(cursor.getInt(cursor.getColumnIndex(filePathColumn[12])));

		}

		cursor.close();
		if (null != picInfo) {

			log(picInfo.toString());
		}
	}

}
