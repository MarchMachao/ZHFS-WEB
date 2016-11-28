package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.RoomInformation;

public interface RoomInformationService {
	/**
	 * 查询所有RoomInformation数据
	 * 
	 * @param startNum
	 * @param rows
	 * @return
	 */
	public List<RoomInformation> getAllUsefulTag(int pageNo, int pageSize);

	/**
	 * 获取数据总数量（用于分页）
	 * 
	 * @return
	 */
	public int countSum();
}
