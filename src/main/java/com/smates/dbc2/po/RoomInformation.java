package com.smates.dbc2.po;

/**
 * 
 * @author lxq
 *
 */
public class RoomInformation {
	private Integer roomId;
	private String roomNum;
	private String roomName;
	private String cpid;
	private String wakeupNum;

	public RoomInformation() {
	}

	public RoomInformation(String roomNum, String roomName, String cpid, String wakeupNum) {
		
		this.roomNum = roomNum;
		this.roomName = roomName;
		this.cpid = cpid;
		this.wakeupNum = wakeupNum;
	}

	public RoomInformation(Integer roomId, String roomNum, String roomName, String cpid, String wakeupNum) {
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.roomName = roomName;
		this.cpid = cpid;
		this.wakeupNum = wakeupNum;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getWakeupNum() {
		return wakeupNum;
	}

	public void setWakeupNum(String wakeupNum) {
		this.wakeupNum = wakeupNum;
	}

}
