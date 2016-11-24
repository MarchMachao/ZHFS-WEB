package com.smates.dbc2.vo;

public class BaseMsg {
	private boolean isSuccess;
	private String content;

	public BaseMsg(boolean isSuccess, String content) {
		super();
		this.isSuccess = isSuccess;
		this.content = content;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
