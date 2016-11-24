package com.smates.dbc2.utils;

import java.util.regex.Pattern;

/**
 * input validater
 */
public class ValidaterUtil {

	public static Pattern telephonePattern = Pattern.compile("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$");

	public static Pattern accountNumberPattern = Pattern.compile("^[A-Za-z0-9]{1,15}$");

	public static Pattern passWordPattern = Pattern.compile("^[A-Za-z0-9!.]{6,40}$");

	public static Pattern eMailPattern = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$");
	
	public static Pattern imagePattern = Pattern.compile("(?i).+?\\.(jpg|gif|bmp|png|jpeg|JPG|GIF|BMP|PNG|JPEG)");
	/**
	 * 判断手机号码的格式
	 * 
	 * @param telephone
	 * @return
	 */
	public static boolean checkTelephone(String telephone) {
		return telephone == null ? false : telephonePattern.matcher(telephone).matches();
	}

	/**
	 * 判断昵称的格式
	 * 
	 * @param nickname
	 * @return
	 */
	public static boolean checkAccountNumber(String accountNumber) {
		return accountNumber == null ? false : accountNumberPattern.matcher(accountNumber).matches();
	}

	/**
	 * 判断密码的格式
	 * 
	 * @param nickname
	 * @return
	 */
	public static boolean checkPassWord(String password) {
		return password == null ? false : passWordPattern.matcher(password).matches();
	}
	
	/**
	 * 判断邮箱的格式
	 * 
	 * @param eMail
	 * @return
	 */
	public static boolean checkEMail(String eMail) {
		return eMail == null ? false : eMailPattern.matcher(eMail).matches();
	}
	
	/**
	 * 判断上传的是否是图片
	 * @param imageName
	 * @return
	 */
	public static boolean checkImage(String imageName){
		return imageName == null ? false : imagePattern.matcher(imageName).matches();
	}


}
