package com.aoeng.degu.utils;

import com.aoeng.degu.domain.LogFileUploadResult;
import com.aoeng.degu.services.BaseTask;
import com.aoeng.degu.services.DataCallback;

public class DataUtils {

	public static void getDateFromServer(FileUploadVO reqVo, DataCallback<LogFileUploadResult> callBack) {
		// TODO Auto-generated method stub
		BaseHandler handler = new BaseHandler(callBack, reqVo);
		BaseTask taskThread = new BaseTask(reqVo, handler);
		UIUtils.getThreadPoolManager().addTask(taskThread);
	}

}
