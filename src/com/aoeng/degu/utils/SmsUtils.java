/**
 * 
 */
package com.aoeng.degu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

/**
 * @author sczhang 2015年1月9日 上午10:56:42
 */
public class SmsUtils {
	final static String SMS_URI_ALL = "content://sms/";
	final static String SMS_URI_INBOX = "content://sms/inbox";
	final static String SMS_URI_SEND = "content://sms/sent";
	final String SMS_URI_DRAFT = "content://sms/draft";
	final String SMS_URI_OUTBOX = "content://sms/outbox";
	final String SMS_URI_FAILED = "content://sms/failed";
	final String SMS_URI_QUEUED = "content://sms/queued";

	public static String getAllSms(ContentResolver contentResolver) {

		// TODO Auto-generated method stub
		StringBuilder smsBuilder = new StringBuilder();
		smsBuilder.append("#address(手机号)|person|body(短信内容)|date(时间；long)|type(接收：发送)").append("\n");
		try {
			Uri uri = Uri.parse(SMS_URI_ALL);
			String[] projection = new String[] { "_id", "address", "person", "body", "date", "type" };
			Cursor cur = contentResolver.query(uri, projection, null, null, "date desc"); // 获取手机内部短信
			if (cur.moveToFirst()) {
				int index_Address = cur.getColumnIndex("address");
				int index_Person = cur.getColumnIndex("person");
				int index_Body = cur.getColumnIndex("body");
				int index_Date = cur.getColumnIndex("date");
				int index_Type = cur.getColumnIndex("type");

				do {
					String strAddress = cur.getString(index_Address);
					int intPerson = cur.getInt(index_Person);
					String strbody = cur.getString(index_Body);
					long longDate = cur.getLong(index_Date);
					int intType = cur.getInt(index_Type);

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date d = new Date(longDate);
					String strDate = dateFormat.format(d);

					String strType = "";
					if (intType == 1) {
						strType = "接收";
					} else if (intType == 2) {
						strType = "发送";
					} else {
						strType = "null";
					}

					smsBuilder.append(strAddress + ", ");
					smsBuilder.append(intPerson + ", ");
					smsBuilder.append(strbody + ", ");
					smsBuilder.append(longDate + ", ");
					smsBuilder.append(strType);
					smsBuilder.append(" \n");
				} while (cur.moveToNext());
				if (!cur.isClosed()) {
					cur.close();
					cur = null;
				}
			} else {
				smsBuilder.append("no result!");
			} // end if
		} catch (SQLiteException ex) {
			Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
		}
		// LogUtils.e(smsBuilder.toString());
		return smsBuilder.toString();
	}

	/**
	 * 保存 短信到 系统 收件箱
	 * 
	 * @param contentResolver
	 * @param tel
	 * @param msg
	 * @param date
	 * @param isSend
	 *            发送的：true;接收到的：false
	 */
	public static void saveSmsToSysDb(ContentResolver contentResolver, String tel, String msg, long date, boolean isSend) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		// 发送时间
		values.put("date", date);
		// 阅读状态
		values.put("read", 0);
		// 1为收 2为发
		values.put("type", isSend ? "2" : "1");
		// 送达号码
		values.put("address", tel);
		// 送达内容
		values.put("body", msg);
		// 插入短信库
		contentResolver.insert(Uri.parse(SMS_URI_ALL), values);
	}

	/**
	 * @param valueOf
	 */
	public static void deleteSmsInSendBoxByDate(ContentResolver contentResolver, Long date) {
		// TODO Auto-generated method stub
		contentResolver.delete(Uri.parse(SMS_URI_ALL), "date=?", new String[] { date + "" });
	}
}
