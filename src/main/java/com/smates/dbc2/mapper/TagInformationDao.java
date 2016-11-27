package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.TagInformation;
import com.smates.dbc2.vo.Page;

public interface TagInformationDao {
	
	/*
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

	/*
	 * 向该表中插入一条数据
	 */
	public void addTagInfoByTagNum(TagInformation tagInfo);
	
	/*
	 * 删除表中的一条数据
	 */
	public void deleteTagInfoByTagNum(Integer tagNum);
	
	/*
	 * 更新表中数据
	 */
	public void updateTagInformation(TagInformation tagInfo);
}
