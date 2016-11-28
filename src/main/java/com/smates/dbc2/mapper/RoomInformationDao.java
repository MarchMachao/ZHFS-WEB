package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.RoomInformation;
import com.smates.dbc2.vo.Page;

public interface RoomInformationDao {
	/**
	 * 查询出房间信息，并进行分页
	 * 
	 * @param page
	 * @return
	 */
	public List<RoomInformation> getAllUsefulTag(Page page);

	/**
	 * 获取数据总数量（用于分页）
	 * 
	 * @return
	 */
	public int countSum();
}
