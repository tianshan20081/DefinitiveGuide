package com.aoeng.degu.ui.imgs;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

public class PictureFromMediaStoreUI extends BaseUI {

	private static final int RESULT_LOAD_IMAGE = 100;
	private ImageView ivPic;
	private Button btnGetImg;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnGetImg:
			Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(intent, RESULT_LOAD_IMAGE);
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_imgs_pic_from_mediastore);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnGetImg = (Button) findView(R.id.btnGetImg);
		ivPic = (ImageView) findView(R.id.ivPic);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnGetImg.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			ivPic.setImageBitmap(BitmapFactory.decodeFile(picturePath));

		}
	}

}
