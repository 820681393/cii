package com.purchase.utils.sms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;

public class SmsDxbManager {
	private static final String username = "1252299775"; //在短信宝注册的用户名
	private static final String password = "xs202088"; //在短信宝注册的密码
	private static final String content = "【EXSMS】您的验证码为%s，在5分钟内有效。";

	private static final String httpUrl = "https://api.smsbao.com/sms";

	private interface SmsDxbManagerInternal {
		public static SmsDxbManager mSms = new SmsDxbManager();
	}



	public static SmsDxbManager getInstance() {
		return SmsDxbManagerInternal.mSms;
	}

	public int getCode(){
		return new Random().nextInt(899999) + 100000;
	}

	public String sendMessage(String mobile, int code){
		StringBuffer httpArg = new StringBuffer();
		httpArg.append("u=").append(username).append("&");
		httpArg.append("p=").append(MD5.encode(password)).append("&");
		httpArg.append("m=").append(mobile).append("&");
		httpArg.append("c=").append(encodeUrlString(String.format(content,code), "UTF-8"));
		return request(httpUrl, httpArg.toString());
	}

	public  String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = reader.readLine();
			if (strRead != null) {
				sbf.append(strRead);
				while ((strRead = reader.readLine()) != null) {
					sbf.append("\n");
					sbf.append(strRead);
				}
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	public  String encodeUrlString(String str, String charset) {
		String strret = null;
		if (str == null)
			return str;
		try {
			strret = java.net.URLEncoder.encode(str, charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return strret;
	}

	public static void main(String[] args) {
		System.out.println(SmsDxbManager.getInstance().sendMessage("18711863813",123456));
	}
}
