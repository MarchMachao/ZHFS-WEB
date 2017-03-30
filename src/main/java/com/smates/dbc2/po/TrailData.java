package com.smates.dbc2.po;

public class TrailData {

	private String tagNum;
	private String startDate;
	private String start;
	private String endDate;
	private String end;
	private String roomNum;
	private String roomName;
	private Integer sub;
	private String color;

	public TrailData() {
	}

	public TrailData(String tagNum, String startDate, String start, String endDate, String end, String roomNum,
			String roomName, Integer sub, String color) {
		this.tagNum = tagNum;
		this.startDate = startDate;
		this.start = start;
		this.endDate = endDate;
		this.end = end;
		this.roomNum = roomNum;
		this.roomName = roomName;
		this.sub = sub;
		this.color = color;
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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

	public Integer getSub() {
		return sub;
	}

	public void setSub(Integer sub) {
		this.sub = sub;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
