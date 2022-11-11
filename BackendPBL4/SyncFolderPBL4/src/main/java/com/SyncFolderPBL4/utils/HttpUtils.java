package com.SyncFolderPBL4.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.SyncFolderPBL4.constant.SystemConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	public static Response messageResponse(Response.Status status, String errorMessage, Gson gson)
	{
		return Response.status(status)
				.entity(gson.toJson(toJsonObject(errorMessage)))
				.type(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
				.build();
	}

}