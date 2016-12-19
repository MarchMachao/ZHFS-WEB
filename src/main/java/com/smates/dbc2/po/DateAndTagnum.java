package com.smates.dbc2.po;

/**
 * mybatis查表时传入数据用的实体类
 * 
 * @author March
 *
 */
public class DateAndTagnum {
	private String date;
	private String tagNum;

	public DateAndTagnum() {
	}

	public DateAndTagnum(String date, String tagNum) {
		this.date = date;
		this.tagNum = tagNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTagNum() {
		return tagNum;
	}

	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}

}
