package com.smates.dbc2.vo;

/**
 * 用于分页的实体
 * 
 * @author machao
 *
 */
public class Page {

	private int startNum;
	private int rows;

	public Page(int page, int rows) {
		this.startNum = (page - 1) * rows;
		this.rows = rows;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
