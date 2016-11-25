package com.smates.dbc2.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroUtils {
	
	/**
	 * 注册时对用户的密码进行加密
	 * @param passwd 明文密码
	 * @return 经过MD5加密后的密码
	 */
	public static String passwdMD5(String passwd){
		String saltSource = SysConst.SALTSOURCE;
		String algorithmName = "MD5";
		String credentials = passwd;
		Object salt = new Md5Hash(saltSource);
		int hashIterations = 1024;
		Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		return result.toString();
	}
	
	public static void main(String[] args){
		System.out.println(passwdMD5("123456"));
	}
	
}
