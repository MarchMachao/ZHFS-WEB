package com.smates.dbc2.utils;

import java.util.UUID;

/**
 * 对String类型操作的工具类
 * 
 * @author baijw12
 *
 */
public class StringUtils {

	/**
	 * 判断字符串是否是null,'null',""中的一个
	 * 
	 * @return 是返回true,不是返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0 || str.equals("null");
	}

	/**
	 * 对用户上传文件的文件名进行重新定义
	 * 
	 * @param fileName
	 * @return
	 */
	public static String formateFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}
		String[] fileExtendName = fileName.split("\\.");
		return UUID.randomUUID().toString()+"."+fileExtendName[fileExtendName.length-1];
	}
	
	

}
