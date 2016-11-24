package com.smates.dbc2.vo;

public class CostumUser {
	private Integer startCount;
	private Integer rows;
	private String accountNumber;
	private String nickName;
	
	public Integer getStartCount() {
		return startCount;
	}
	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public CostumUser() {
	}
	@Override
	public String toString() {
		return "CostumUser [startCount=" + startCount + ", rows=" + rows + ", accountNumber=" + accountNumber
				+ ", nickName=" + nickName + "]";
	}
}
