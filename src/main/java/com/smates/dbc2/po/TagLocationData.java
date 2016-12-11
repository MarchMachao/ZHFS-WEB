package com.smates.dbc2.po;

/**
 * 标签定位数据+各种其他数据实体类
 * 
 * @author March
 *
 */

public class TagLocationData {

	private String tagNum;
	private String startDate;
	private String start;
	private String name;
	private String sex;
	private Integer age;
	private String maritalStatus;
	private String roomNum;
	private String roomName;
	private String end;
	private String endDate;
	private String health;
	private String image;

	public TagLocationData() {
	}


	public TagLocationData(String tagNum, String startDate, String start, String name, String sex, Integer age,
			String maritalStatus, String roomNum, String roomName, String end, String endDate, String health,
			String image) {
		super();
		this.tagNum = tagNum;
		this.startDate = startDate;
		this.start = start;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.roomNum = roomNum;
		this.roomName = roomName;
		this.end = end;
		this.endDate = endDate;
		this.health = health;
		this.image = image;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTagNum() {
		return tagNum;
	}

	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
