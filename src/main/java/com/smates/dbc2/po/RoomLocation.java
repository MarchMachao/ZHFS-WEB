package com.smates.dbc2.po;

/**
 * 查询所有的用户目前在房间的位置
 * 
 * @author 刘晓庆
 *
 */
public class RoomLocation {
	private String tagNum;
	private String roomName;
	private Integer roomNum;

	public RoomLocation() {
		super();
	}

	public RoomLocation(String tagNum, String roomName, Integer roomNum) {
		this.tagNum = tagNum;
		this.roomName = roomName;
		this.roomNum = roomNum;
	}

	public String getTagNum() {
		return tagNum;
	}

	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "RoomLocation [tagNum=" + tagNum + ", roomName=" + roomName + ", roomNum=" + roomNum + "]";
	}

}
