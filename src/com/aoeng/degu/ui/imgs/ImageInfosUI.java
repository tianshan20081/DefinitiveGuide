/**
 * 
 */
package com.aoeng.degu.ui.imgs;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

/**
 * @author sczhang 2015年1月26日 下午2:43:38
 */
public class ImageInfosUI extends BaseUI {

	private TextView tvImgInfo;
	private ImageView ivInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_imgs_info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		tvImgInfo = (TextView) findView(R.id.tvImgInfo);
		ivInfo = (ImageView) findView(R.id.ivInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		String path = FileUtils.getDownloadDir() + "01.jpg";
		BitmapUtils utils = new BitmapUtils(this);
		BitmapDisplayConfig displayConfig = new BitmapDisplayConfig();
		displayConfig.setAutoRotation(true);
		utils.display(ivInfo, path, displayConfig);

		// TODO Auto-generated method stub

		// Bitmap bitmap = BitmapFactory.decodeFile(path);
		// Config config = bitmap.getConfig();
		// for (Config i : Config.values()) {
		// LogUtils.e("i.name()" + i.name() + i.valueOf(i.name()));
		// }
		int angle = 0;
		try {
			LogUtils.e("path ---" + path);
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			LogUtils.e("--orientation-" + orientation);

			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				angle = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				angle = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				angle = 270;
				break;
			}
			LogUtils.e("--angle--" + angle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (angle != 0) {
			try {
				Matrix matrix = new Matrix();
				matrix.setRotate(angle);
				Bitmap srcBitmap = BitmapFactory.decodeFile(path);
				Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
				File destFile = new File(FileUtils.getDownloadDir() + "01_.jpg");
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				bos.flush();
				bos.close();
				srcBitmap.recycle();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
