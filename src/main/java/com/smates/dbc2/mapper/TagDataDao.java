package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.DateAndTagnum;
import com.smates.dbc2.po.PieData;
import com.smates.dbc2.po.TagLocationData;
import com.smates.dbc2.po.TrailData;

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

	/**
	 * 查询饼图数据
	 * 
	 * @param dateAndTagnum
	 * @return
	 */
	public List<PieData> getPieData(DateAndTagnum dateAndTagnum);

	/**
	 * 查询某用户某天的运动轨迹
	 * 
	 * @param dateAndTagnum
	 * @return
	 */
	public List<TrailData> getTrailByDateAndTagnum(DateAndTagnum dateAndTagnum);
}
