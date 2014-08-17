package com.aoeng.degu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.aoeng.degu.domain.LogFileUploadResult;

public class LogFileUploadParser extends BaseParser {

	@Override
	public Object parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			JSONObject jsonObject = new JSONObject(paramString);
			String json = jsonObject.getString("result");
			return JSON.parseObject(json, LogFileUploadResult.class);
		}
		return null;
	}

}
