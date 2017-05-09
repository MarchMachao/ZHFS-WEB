package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.RoomLocation;

/**
 * 用户位置
 * 
 * @author 刘晓庆
 *
 */
public interface RoomLocationDao {
	/**
	 * 查询位置信息
	 * 
	 * @return
	 */
	public List<RoomLocation> getRoomLocation();
}
