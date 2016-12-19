package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.DateAndTagnum;
import com.smates.dbc2.po.PieData;
import com.smates.dbc2.po.TagLocationData;

/**
 * 标签各种定位数据的Service
 * 
 * @author March
 *
 */
public interface TagDataService {

	/**
	 * 查询所有标签的定位信息
	 * 
	 * @return
	 */
	public List<TagLocationData> getAllLocation();

	/**
	 * 查询饼图数据
	 * 
	 * @param dateAndTagnum
	 * @return
	 */
	public List<PieData> getPieData(DateAndTagnum dateAndTagnum);
}
