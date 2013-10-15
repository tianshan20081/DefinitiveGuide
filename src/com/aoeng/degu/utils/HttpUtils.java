/**
 * 
 */
package com.aoeng.degu.utils;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author [ShaoCheng Zhang] Oct 15, 2013:4:28:27 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class HttpUtils {
	private static HttpClient client = new DefaultHttpClient();
	private String reqUrl = "";

	/**
	 * HttpPost HttpClient FutureTask
	 * 
	 * @param url
	 *            请求　url
	 * @param rawParams
	 *            服务器响应字符串
	 * @return
	 * @throws Exception
	 */
	public static String postRequest(final String url, final Map<String, String> rawParams) throws Exception {
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				HttpPost post = new HttpPost(url);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				for (String key : rawParams.keySet()) {
					pairs.add(new BasicNameValuePair(key, rawParams.get(key)));
				}
				post.setEntity(new UrlEncodedFormEntity(pairs, "gbk"));
				HttpResponse response = client.execute(post);
				if (null != response && response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
					String result = EntityUtils.toString(response.getEntity());
					return result;
				}
				return null;
			}
		});

		new Thread(task).start();
		return task.get();
	}

	/**
	 * HttpGet HttpClient FutureTask
	 * 
	 * @param url
	 *            请求　url
	 * @param rawParams
	 *            服务器响应字符串
	 * @return
	 * @throws Exception
	 */
	public static String getRequest(final String url) throws Exception {
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				HttpGet post = new HttpGet(url);
				HttpResponse response = client.execute(post);
				if (null != response && response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
					String result = EntityUtils.toString(response.getEntity());
					return result;
				}
				return null;
			}
		});

		new Thread(task).start();
		return task.get();
	}

}
