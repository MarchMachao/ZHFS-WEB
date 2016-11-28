package com.smates.dbc2.vo;

import java.util.List;

public class DataGrideRow<T> {
	private int total;
	private List<T> rows;
	
	public DataGrideRow(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void rows(List<T> rows) {
		this.rows = rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "DataGrideRow [total=" + total + ", rows=" + rows + "]";
	}
	
	
}
