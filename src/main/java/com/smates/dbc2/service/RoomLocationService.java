package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.RoomLocation;

public interface RoomLocationService {
	/**
	 * 查询位置信息
	 * 
	 * @return
	 */
	public List<RoomLocation> getRoomLocation();
}
