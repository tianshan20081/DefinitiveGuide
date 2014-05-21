/**
 * 
 */
package com.aoeng.degu.utils;

import java.util.HashMap;

import com.aoeng.degu.parser.BaseParser;

import android.content.Context;

/**
 * Mar 23, 2014 11:51:00 AM
 * 
 */
public class RequestVO {
	public int requestUrl;
	public Context context;
	public HashMap<String, String> requestDataMap;
	public BaseParser<?> jsonParser;

	public RequestVO() {
	}

	public RequestVO(int requestUrl, Context context, HashMap<String, String> requestDataMap, BaseParser<?> jsonParser) {
		super();
		this.requestUrl = requestUrl;
		this.context = context;
		this.requestDataMap = requestDataMap;
		this.jsonParser = jsonParser;
	}
}
