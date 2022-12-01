package com.SyncFolderPBL4.utils;

import java.io.File;

public class StringUtils {
	private StringUtils() {
		// TODO Auto-generated constructor stub
	}

	public static String cutLastElementPath(String source) {
		String result = source.substring(0, source.lastIndexOf("\\"));
		return result;
	}
}
