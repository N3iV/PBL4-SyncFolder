package com.SyncFolderPBL4.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.SyncFolderPBL4.constant.SystemConstant;
import com.google.gson.Gson;

public class HttpUtils {
	private HttpUtils() {
	}

	public static String getjson(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}

		return sb.toString();
	}

	public static String getPathURL(String s) {
		String[] sarr = s.split("/");
		return sarr[sarr.length - 1];
	}

	public static Map<String, Object> toJsonObject(String s) {
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SystemConstant.MESSAGE, s);
		return map;
	}

}