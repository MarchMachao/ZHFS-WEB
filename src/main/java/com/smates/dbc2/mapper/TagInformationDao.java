package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.TagInformation;
import com.smates.dbc2.vo.Page;

public interface TagInformationDao {
	
	/**
	 * 通过标签号获取该标签持有人的信息
	 * @return
	 */
	public List<TagInformation> getAllUsefulTag(Page page);
	
	/**
	 * 获取数据总数量（用于分页）
	 * 
	 * @return
	 */
	public int countSum();

	/**
	 * 向该表中插入一条数据
	 */
	public void addTagInfoByTagNum(TagInformation tagInfo);
	
	/**
	 * 根据标签号删除数据
	 * @param tagNum
	 */
	public void deleteTagInfoByTagNum(Integer tagNum);
	
	/**
	 * 更新标签持有人信息
	 * @param tagInfo
	 */
	public void updateTagInformation(TagInformation tagInfo);
}
