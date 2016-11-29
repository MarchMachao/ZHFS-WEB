package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.RoomInformation;
import com.smates.dbc2.vo.Page;
/**
 * 
 * @author wrz
 *
 */
public interface RoomInformationDao {
	/**
	 * 查询出房间信息，并进行分页
	 * 
	 * @param page
	 * @return
	 */
	public List<RoomInformation> getAllUsefulRoom(Page page);

	/**
	 * 获取数据总数量（用于分页）
	 * 
	 * @return
	 */
	public int countSum();
	/**
	 * 用于插入一条房间信息
	 * @param roomInfo
	 */
	public void addRoomInfo(RoomInformation roomInfo);
	/**
	 * 根据roomId删除一条房间记录
	 * @param roomId
	 */
	public void delRoomInfoByRoomId(Integer roomId);
	/**
	 * 修改一条房间记录
	 * @param roomInfo
	 */
	public void updRoomInfo(RoomInformation roomInfo);
}
