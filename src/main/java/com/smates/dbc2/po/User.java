package com.smates.dbc2.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String accountNumber;

	private String nickName;

	private String password;

	private Integer role;

	private Date createDate;

	private String eMail;

	private String image;

	public User(Integer id, String accountNumber, String nickName, String password, Integer role, Date createDate,
			String eMail) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.nickName = nickName;
		this.password = password;
		this.role = role;
		this.createDate = createDate;
		this.eMail = eMail;
		this.image = "000.jpg";
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", accountNumber=" + accountNumber + ", nickName=" + nickName + ", password="
				+ password + ", role=" + role + ", createDate=" + createDate + ", eMail=" + eMail
				+ "]";
	}
}
