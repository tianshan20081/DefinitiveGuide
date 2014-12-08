/**
 * 
 */
package com.aoeng.degu.ui.media;

/**
 * @author sczhang	
 * 2014年12月8日	下午2:56:48
 * @Email {zhangshch0131@126.com}
 */
/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.LogUtils;
import com.google.zxing.client.android.camera.CameraConfigurationUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public final class ZXingTestUI extends BaseUI {
	private static final String TAG = ZXingTestUI.class.getSimpleName();
	private static final String PACKAGE_NAME = ZXingTestUI.class.getPackage().getName();

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
		setContentView(R.layout.ui_media_zxing_test);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findViewById(R.id.get_camera_parameters).setOnClickListener(getCameraParameters);
		findViewById(R.id.scan_product).setOnClickListener(scanProduct);
		findViewById(R.id.scan_qr_code).setOnClickListener(scanQRCode);
		findViewById(R.id.scan_anything).setOnClickListener(scanAnything);
		findViewById(R.id.search_book_contents).setOnClickListener(searchBookContents);
		findViewById(R.id.encode_url).setOnClickListener(encodeURL);
		findViewById(R.id.encode_email).setOnClickListener(encodeEmail);
		findViewById(R.id.encode_phone).setOnClickListener(encodePhone);
		findViewById(R.id.encode_sms).setOnClickListener(encodeSMS);
		findViewById(R.id.encode_contact).setOnClickListener(encodeContact);
		findViewById(R.id.encode_location).setOnClickListener(encodeLocation);
		findViewById(R.id.encode_hidden_data).setOnClickListener(encodeHiddenData);
		findViewById(R.id.encode_bad_data).setOnClickListener(encodeBadData);
		findViewById(R.id.share_via_barcode).setOnClickListener(shareViaBarcode);
		findViewById(R.id.run_benchmark).setOnClickListener(runBenchmark);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (result != null) {
			String contents = result.getContents();
			if (contents != null) {
				showDialog(R.string.result_succeeded, result.toString());
			} else {
				showDialog(R.string.result_failed, getString(R.string.result_failed_why));
			}
		}
	}

	private final View.OnClickListener getCameraParameters = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String stats = CameraConfigurationUtils.collectStats(getFlattenedParams());
			writeStats(stats);
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL, "zxing-external@google.com");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Camera parameters report");
			intent.putExtra(Intent.EXTRA_TEXT, stats);
			intent.setType("text/plain");
			startActivity(intent);
		}
	};
	private final View.OnClickListener runBenchmark = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setClassName(ZXingTestUI.this, BenchmarkUI.class.getName());
			startActivity(intent);
		}
	};
	private final View.OnClickListener scanProduct = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			IntentIntegrator integrator = new IntentIntegrator(ZXingTestUI.this);
			integrator.addExtra("SCAN_WIDTH", 800);
			integrator.addExtra("SCAN_HEIGHT", 200);
			integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
			integrator.addExtra("PROMPT_MESSAGE", "Custom prompt to scan a product");
			integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
		}
	};
	private final View.OnClickListener scanQRCode = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			IntentIntegrator integrator = new IntentIntegrator(ZXingTestUI.this);
			integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
		}
	};
	private final View.OnClickListener scanAnything = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			IntentIntegrator integrator = new IntentIntegrator(ZXingTestUI.this);
			integrator.initiateScan();
		}
	};
	private final View.OnClickListener searchBookContents = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent("com.google.zxing.client.android.SEARCH_BOOK_CONTENTS");
			intent.putExtra("ISBN", "9780441014989");
			intent.putExtra("QUERY", "future");
			startActivity(intent);
		}
	};
	private final View.OnClickListener encodeURL = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			encodeBarcode("TEXT_TYPE", "http://www.nytimes.com");
		}
	};
	private final View.OnClickListener encodeEmail = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			encodeBarcode("EMAIL_TYPE", "foo@example.com");
		}
	};
	private final View.OnClickListener encodePhone = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			encodeBarcode("PHONE_TYPE", "2125551212");
		}
	};
	private final View.OnClickListener encodeSMS = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			encodeBarcode("SMS_TYPE", "2125551212");
		}
	};
	private final View.OnClickListener encodeContact = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Bundle bundle = new Bundle();
			bundle.putString(ContactsContract.Intents.Insert.NAME, "Jenny");
			bundle.putString(ContactsContract.Intents.Insert.PHONE, "8675309");
			bundle.putString(ContactsContract.Intents.Insert.EMAIL, "jenny@the80s.com");
			bundle.putString(ContactsContract.Intents.Insert.POSTAL, "123 Fake St. San Francisco, CA 94102");
			encodeBarcode("CONTACT_TYPE", bundle);
		}
	};
	private final View.OnClickListener encodeLocation = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Bundle bundle = new Bundle();
			bundle.putFloat("LAT", 40.829208f);
			bundle.putFloat("LONG", -74.191279f);
			encodeBarcode("LOCATION_TYPE", bundle);
		}
	};
	private final View.OnClickListener encodeHiddenData = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			IntentIntegrator integrator = new IntentIntegrator(ZXingTestUI.this);
			integrator.addExtra("ENCODE_SHOW_CONTENTS", false);
			integrator.shareText("SURPRISE!");
		}
	};
	private final View.OnClickListener encodeBadData = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			encodeBarcode(null, "bar");
		}
	};
	private final View.OnClickListener shareViaBarcode = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent("com.google.zxing.client.android.SHARE"));
		}
	};

	private void showDialog(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(R.string.ok_button, null);
		builder.show();
	}

	private void encodeBarcode(CharSequence type, CharSequence data) {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.shareText(data, type);
	}

	private void encodeBarcode(CharSequence type, Bundle data) {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.addExtra("ENCODE_DATA", data);
		integrator.shareText(data.toString(), type); // data.toString() isn't
														// used
	}

	private static CharSequence getFlattenedParams() {
		Camera camera = Camera.open();
		if (camera == null) {
			return null;
		}
		try {
			Camera.Parameters parameters = camera.getParameters();
			if (parameters == null) {
				return null;
			}
			return parameters.flatten();
		} finally {
			camera.release();
		}
	}

	private static void writeStats(String resultString) {
		File cameraParamsFile = new File(Environment.getExternalStorageDirectory().getPath() + "/CameraParameters.txt");
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(cameraParamsFile), Charset.forName("UTF-8"));
			out.write(resultString);
		} catch (IOException e) {
			LogUtils.e(TAG + "Cannot write parameters file ", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LogUtils.w(e);
				}
			}
		}
	}
}