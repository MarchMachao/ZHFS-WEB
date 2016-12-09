package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.TagLocationData;

/**
 * 各种定位数据处理Dao
 * 
 * @author March
 *
 */
public interface TagDataDao {
	/**
	 * 查询所有标签的定位信息，顺便查出标签基本信息
	 * 
	 * @return
	 */
	public List<TagLocationData> getAllLocation();
}
