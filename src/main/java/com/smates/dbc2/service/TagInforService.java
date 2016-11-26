package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.TagInformation;

public interface TagInforService {

	/*
	 * 通过标签号获取该标签持有人的信息
	 * @return
	 */
	public List<TagInformation> getAllUsefulTag();
	
	/*
	 * 向该表中插入一条数据
	 */
	public void addTagInfoByTagNum(String tagNum, String name, Integer age, String sex, String maritalStatus);
	
	/*
	 * 删除表中的一条数据
	 */
	public void deleteTagInfoByTagNum(Integer tagNum);
	
	/*
	 * 更新表中数据
	 */
	public void updateTagInformation(TagInformation tagInfo);
}
