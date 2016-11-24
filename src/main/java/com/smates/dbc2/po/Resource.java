package com.smates.dbc2.po;

import java.util.Date;

public class Resource{
	
	private String id;
	private String type;
	private String name;
	private String content;
	private String describe;
	private String owner;
	private Date createTime;
	private String url;
	private String permitAccountNum;

	public Resource() {
	}

	public Resource(String id, String type, String name, String content, String describe, String owner,
			Date createTime, String url, String permitAccountNum) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.content = content;
		this.describe = describe;
		this.owner = owner;
		this.createTime = createTime;
		this.url = url;
		this.permitAccountNum = permitAccountNum;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermitAccountNum() {
		return permitAccountNum;
	}

	public void setPermitAccountNum(String permitAccountNum) {
		this.permitAccountNum = permitAccountNum;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", type=" + type + ", name=" + name + ", content=" + content + ", describe="
				+ describe + ", owner=" + owner + ", createTime=" + createTime + "]";
	}
	
}
