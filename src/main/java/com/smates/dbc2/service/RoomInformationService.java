package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.RoomInformation;
/**
 * 
 * @author wrz
 *
 */
public interface RoomInformationService {
	/**
	 * 查询所有RoomInformation数据
	 * 
	 * @param startNum
	 * @param rows
	 * @return
	 */
	public List<RoomInformation> getAllUsefulRoomInfo(int pageNo, int pageSize);
	
	/**
	 * 插入一条房间信息
	 * 
	 * @param roomNum
	 * @param roomName
	 * @param cpid
	 * @param wakeupNum
	 */
	public void addRoomInfo(String roomNum , String roomName , String cpid , String wakeupNum);
	/**
	 * 获取数据总数量（用于分页）
	 * 
	 * @return
	 */
	
	public int countSum();
	/**
	 * 删除一条房间记录
	 * @param roomId
	 */
	public void delRoomInfoByRoomId(Integer roomId);
	
	/**
	 * 
	 * @param id
	 * @param roomNum
	 * @param roomName
	 * @param cpid
	 * @param wakeupNum
	 */
	public void updRoomInfo(Integer roomId, String roomNum, String roomName, String cpid, String wakeupNum);

	
}
