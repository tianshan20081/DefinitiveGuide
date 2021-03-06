package com.aoeng.degu.ui.shell;

import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.IOUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;

import java.io.IOException;
import java.io.InputStream;

public class ShellHomeUI extends BaseUI {

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnWifiInfo:
                String shell = "cat /data/misc/wifi/wpa_supplicant.conf > /mnt/sdcard/df/wifi.log";
                String result = execute(shell);
                if (!StringUtils.isEmpty(result)) {
                    LogUtils.e("----->" + result);
                }
                break;

            default:
                break;
        }
    }

    private String execute(String shell) {
        // TODO Auto-generated method stub
        Runtime runTime = Runtime.getRuntime();
        try {
            Process process = runTime.exec(shell);
            InputStream is = process.getInputStream();
            InputStream errIs = process.getErrorStream();
            String errMsg = IOUtils.getStrFromInputStream(errIs);
            LogUtils.e("error msg" + errMsg);
            String msg = IOUtils.getStrFromInputStream(is);
            return msg;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_shell_home);

    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        findView(R.id.btnWifiInfo).setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

}
