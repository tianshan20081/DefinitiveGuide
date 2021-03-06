package com.aoeng.degu.ui.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.application.DGApplication;
import com.aoeng.degu.domain.OperationData;
import com.aoeng.degu.domain.TestData;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.UIUtils;
import com.chronocloud.ryfibluetoothlibrary.BluetoothOpration;
import com.chronocloud.ryfibluetoothlibrary.entity.TestDataInfo;
import com.chronocloud.ryfibluetoothlibrary.entity.User;
import com.chronocloud.ryfibluetoothlibrary.listener.BluetoothOprationCallback;

public class BleShiYunHomeUI extends BaseUI {

	public static final String NO = "no";
	public static final String ORDER = "order";
	public static final String NAME = "name";
	private BluetoothOpration mBluetoothOpration;
	private ListView lvData;
	private RadioGroup rg_sex;
	private EditText et_height;
	private EditText et_age;
	private Button btn_send;
	private LinearLayout ll_test;
	private TextView scale_weight;
	private TextView tv_mac_address;
	private Button btn_read;
	private Button btn_write;
	private EditText et_number;
	private ArrayList<Map<String, String>> data;
	private SimpleAdapter adapter;
	private BluetoothDevice mBluetoothDevice;
	protected String sex;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method
		// stub
		switch (v.getId()) {
		case R.id.btn_read:
			mBluetoothOpration.ReadNumber();
			break;
		case R.id.btn_write:
			mBluetoothOpration.writeNumber(et_number.getText().toString().trim());
			break;
		case R.id.btn_send:
			String height = et_height.getText().toString();
			String age = et_age.getText().toString();
			if (mBluetoothOpration != null && (!height.equals("") && !age.equals(""))
					&& (Integer.parseInt(height) >= 100 && Integer.parseInt(height) <= 220)
					&& (Integer.parseInt(age) >= 10 && Integer.parseInt(age) <= 80)) {
				mBluetoothOpration.selectUserScale("09", height, age, sex);
			} else {
				Toast.makeText(context, R.string.age_height, Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btnConnect:
			if (mBluetoothDevice != null) {
				LogUtils.e("mBluetoothOpration.connect(mBluetoothDevice)");
				LogUtils.e(mBluetoothDevice.getName());
				LogUtils.e(mBluetoothDevice.getAddress());
				UIUtils.toastShow(mBluetoothDevice.getName());
				mBluetoothOpration.connect(mBluetoothDevice);
			}

			break;
		case R.id.btnDisConnect:
			mBluetoothOpration.disconnect();
			LogUtils.e("onclick disconnection");
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method
		// stub

		setContentView(R.layout.ui_ble_shiyun_home);

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method
		// stub
		lvData = (ListView) findViewById(R.id.lv_data);
		rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
		et_height = (EditText) findViewById(R.id.et_height);
		et_age = (EditText) findViewById(R.id.et_age);
		btn_send = (Button) findViewById(R.id.btn_send);
		ll_test = (LinearLayout) findViewById(R.id.ll_test);
		scale_weight = (TextView) findViewById(R.id.scale_weight);
		tv_mac_address = (TextView) findViewById(R.id.tv_mac_address);
		// et_value = (EditText)
		// findViewById(R.id.et_value);
		// btn_send1 = (Button)
		// findViewById(R.id.btn_send1);
		btn_read = (Button) findViewById(R.id.btn_read);
		btn_write = (Button) findViewById(R.id.btn_write);
		et_number = (EditText) findViewById(R.id.et_number);
		data = new ArrayList<Map<String, String>>();
		adapter = new SimpleAdapter(context, data, android.R.layout.simple_list_item_1, new String[] { "name" }, new int[] { android.R.id.text1 });
		lvData.setAdapter(adapter);

		Intent intent = getIntent();
		if (null == intent) {
			finish();
			return;

		}
		mBluetoothDevice = intent.getParcelableExtra("deviceInfo");
		if (null == mBluetoothDevice) {
			finish();
			return;
		}

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method
		// stub

		btn_read.setOnClickListener(this);
		btn_send.setOnClickListener(this);
		btn_write.setOnClickListener(this);
		findView(R.id.btnConnect).setOnClickListener(this);
		findView(R.id.btnDisConnect).setOnClickListener(this);
		lvData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated
				// method stub
				HashMap<String, String> map = (HashMap<String, String>) parent.getAdapter().getItem(position);
				String order = map.get(ORDER);
				if (order.equals(OperationData.VIEW_ALL_USERS)) {
					Intent intent1 = new Intent(context, BleShiYunUserUI.class);
					startActivity(intent1);
				} else if (order.equals(OperationData.PURE_GUEST_MODE)) {
					mBluetoothOpration.pureGestMode();
				} else if (order.equals(OperationData.QUIT_PURE_GUEST_MODE)) {
					mBluetoothOpration.quitPureGuestMode();
				} else if (order.equals(OperationData.READ_MAC_ADDRESS)) {
					mBluetoothOpration.readMacAddress();
				} else if (order.equals(OperationData.RESET_SCALE_PARAM)) {
					mBluetoothOpration.resetScaleParam();
				} else if (order.equals(OperationData.ZERO)) {
					mBluetoothOpration.zero();
				}
			}
		});

		rg_sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated
				// method stub
				switch (group.getCheckedRadioButtonId()) {
				case R.id.rb_man:
					sex = "01";
					break;
				case R.id.rb_woman:
					sex = "00";
					break;
				}
			}
		});
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method
		// stub
		mBluetoothOpration = DGApplication._BluetoothOpration;
		mBluetoothOpration.addBluetoothOprationCallback(BOcallback);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mBluetoothOpration.removeBluetoothOprationCallback(BOcallback);
		mBluetoothOpration.disconnect();
		mBluetoothOpration.onDestroy();
		LogUtils.e("We are in destroy");
	}

	BluetoothOprationCallback BOcallback = new BluetoothOprationCallback() {

		private boolean isPause;
		private HashMap<String, String> testMap;
		private boolean mConnected;

		@Override
		public void onWeight(int staut, double weight) {
			// TODO Auto-generated
			// method stub
			scale_weight.setText(weight + "kg");
			if (staut == 0) {
				scale_weight.setTextColor(Color.RED);
			} else {
				scale_weight.setTextColor(Color.BLACK);
			}
		}

		@Override
		public void onUserIsExist(int staut) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onUpdateUser(int staut) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onTestDataInfo(TestDataInfo dataInfo) {
			// TODO Auto-generated
			// method stub
			if (isPause) {
				return;
			}
			testMap = new HashMap<String, String>();
			testMap.put(TestData.TIME, dataInfo.getTime());
			testMap.put(TestData.WEIGHT, dataInfo.getWeight());
			testMap.put(TestData.BF, dataInfo.getBf());
			testMap.put(TestData.WATRER, dataInfo.getWatrer());
			testMap.put(TestData.MUSCLE, dataInfo.getMuscle());
			testMap.put(TestData.BONE, dataInfo.getBone());
			testMap.put(TestData.BMR, dataInfo.getBmr());
			testMap.put(TestData.SFAT, dataInfo.getSfat());
			testMap.put(TestData.INFAT, dataInfo.getInfat());
			testMap.put(TestData.BODYAGE, dataInfo.getBodyage());
			startTestActivity();
		}

		@Override
		public void onSelectUserScale(List<TestDataInfo> listDataInfo) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onSelectUserScale(int staut) {
			// TODO Auto-generated
			// method stub
			if (staut == 0) {
				btn_send.setClickable(false);
				btn_send.setTextColor(getResources().getColor(android.R.color.black));
				Toast.makeText(context, R.string.select_success, Toast.LENGTH_SHORT).show();
			} else {
				btn_send.setClickable(true);
				btn_send.setTextColor(getResources().getColor(android.R.color.white));
				Toast.makeText(context, R.string.choose_failure, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onSelectAllUser(Context context, Intent intent, List<User> listUser) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onGetUserInfo(User user) {
			// TODO Auto-generated
			// method stub
			LogUtils.e("UserInfo " + user.toString());
		}

		@Override
		public void onDisconnected(Context context, Intent intent) {
			// TODO Auto-generated
			// method stub
			mConnected = false;
			invalidateOptionsMenu();
			data.clear();
			// setData(NO,
			// "No Connection");
			ll_test.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onDeleteUserScale(int staut) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onDeleteUser(int staut) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onCreateNewUser(int staut) {
			// TODO Auto-generated
			// method stub

		}

		@Override
		public void onConnectSuccess(Context context, Intent intent) {
			// TODO Auto-generated
			// method stub

			LogUtils.e("onConnectSuccess");
			UIUtils.toastShow("onConnectSuccess");
			mConnected = true;
			ShowDialog();
			invalidateOptionsMenu();

			data.clear();
			setData(OperationData.VIEW_ALL_USERS, "View all users");
			setData(OperationData.PURE_GUEST_MODE, "Pure guest mode");
			setData(OperationData.QUIT_PURE_GUEST_MODE, "Quit Pure guest mode");
			setData(OperationData.READ_MAC_ADDRESS, "Read MAC address");
			setData(OperationData.RESET_SCALE_PARAM, "Reset scale param");
			setData(OperationData.ZERO, "Zero");
			// setData("00","ReaderUpdateValue");
			adapter.notifyDataSetChanged();
			ll_test.setVisibility(View.VISIBLE);
		}

		@Override
		public void onPureGuestMode(int staut) {
			// TODO Auto-generated
			// method stub
			if (staut == 0) {
				Toast.makeText(context, "Setting Pure guest mode success!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "Setting Pure guest mode error!", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onQuitPureGuestMode(int staut) {
			// TODO Auto-generated
			// method stub
			if (staut == 0) {
				Toast.makeText(context, "Quit Pure guest mode success!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "Quit Pure guest mode error!", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onReadMacAddress(String macAddress) {
			// TODO Auto-generated
			// method stub
			tv_mac_address.setText("Mac:" + macAddress);
		}

		@Override
		public void onResetScaleParam(int staut) {
			// TODO Auto-generated
			// method stub
			if (staut == 0) {
				Toast.makeText(context, "Reset Success!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "Reset Error!", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onZero(int staut) {
			// TODO Auto-generated
			// method stub
			if (staut == 0) {
				Toast.makeText(context, "Zero success!", Toast.LENGTH_SHORT).show();
			} else if (staut == 1) {
				Toast.makeText(context, "Zero error!", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onReadNumber(String number) {
			// TODO Auto-generated
			// method stub
			et_number.setText(number);
		}

		@Override
		public void onWriteNumber(int staut) {
			// TODO Auto-generated
			// method stub
			if (staut == 4) {
				Toast.makeText(context, "write success!", Toast.LENGTH_SHORT).show();
			}
		}

	};

	private List<Map<String, String>> setData(String order, String name) {
		if (data == null) {
			data = new ArrayList<Map<String, String>>();
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(ORDER, order);
		map.put(NAME, name);
		data.add(map);

		return data;
	}

	private void ShowDialog() {
		Toast.makeText(this, R.string.connection_successful, Toast.LENGTH_SHORT).show();
	}

	private void startTestActivity() {
		// btn_send.setClickable(true);
		// btn_send.setTextColor(getResources().getColor(android.R.color.white));
		// Intent intent = new
		// Intent(context,
		// TestDataActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putSerializable("testMap",
		// (Serializable) testMap);
		// intent.putExtra("bundle",
		// bundle);
		// startActivity(intent);
	}
}
