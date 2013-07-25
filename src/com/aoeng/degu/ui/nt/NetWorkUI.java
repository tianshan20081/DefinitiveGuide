package com.aoeng.degu.ui.nt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.ViewUtils;

public class NetWorkUI extends Activity implements OnClickListener {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ViewUtils.toastCenter(NetWorkUI.this, "可用端口：" + msg.what, false);
		};
	};
	private Button btnScanServerPort;
	private EditText etClientInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_nt_home);
		btnScanServerPort = (Button) this.findViewById(R.id.btnScanServerPort);
		this.findViewById(R.id.btnScanServerPort).setOnClickListener(this);
		etClientInfo = (EditText) this.findViewById(R.id.etClientInfo);
		this.findViewById(R.id.btnSendMsgFromClient).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSendMsgFromClient:
			String msg = etClientInfo.getText().toString().trim();
			if (TextUtils.isEmpty(msg)) {
				ViewUtils.toast(this, "Please input the Send message !", false);
				return;
			}
			try {
				Socket socket = new Socket("192.168.7.247", 10000);
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bufferedWriter = new BufferedWriter(osw);
				bufferedWriter.write(msg);
				bufferedWriter.flush();

				InputStream in = socket.getInputStream();
				InputStreamReader ins = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(ins);
				String serverMsg = "";
				String msgStr = "";
				while ((msgStr = bufferedReader.readLine()) != null) {
					serverMsg += msgStr;
					ViewUtils.toast(this, msgStr, false);
				}
				ViewUtils.toast(this, serverMsg, false);
			} catch (Exception e) {
				// TODO: handle exception
			}

			break;
		case R.id.btnScanServerPort:
			new Thread(new ScanPorts(1, 65535)).start();
			ViewUtils.toastCenter(this, "开始扫描", false);
			btnScanServerPort.setEnabled(false);
			break;

		default:
			break;
		}
	}

	class ScanPorts extends Thread {
		private int minPort, maxPort;
		private String str = "可用端口有：";

		public ScanPorts(int minPort, int maxPort) {
			this.maxPort = maxPort;
			this.minPort = minPort;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			for (int i = minPort; i < maxPort; i++) {
				try {
					Socket socket = new Socket();
					SocketAddress socketAddress = new InetSocketAddress("210.73.90.26", i);
					socket.connect(socketAddress, 50);
					ViewUtils.toastCenter(NetWorkUI.this, "可用端口" + i, false);
					handler.sendEmptyMessage(i);
					socket.close();
					str += i + ";";
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ViewUtils.toastCenter(NetWorkUI.this, "扫描完成" + str, false);
					ViewUtils.log(NetWorkUI.class.getName().toUpperCase(), str);
					btnScanServerPort.setEnabled(true);
				}
			});
			super.run();
		}
	}
}
