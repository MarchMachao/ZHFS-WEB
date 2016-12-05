package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.TagInformation;

public interface TagInforService {

	/**
	 * 获取所有有效标签信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<TagInformation> getAllUsefulTag(int pageNo, int pageSize);
	
	/**
	 * 向表中插入一条tag数据
	 * 
	 * @param tagNum
	 * @param name
	 * @param age
	 * @param sex
	 * @param maritalStatus
	 */
	public void addTagInfoByTagNum(String tagNum,String name, Integer age, String sex, String maritalStatus);
	
	/**
	 * 获取数据总数量（用于分页）
	 * 
	 * @return
	 */
	public int countSum();

	/**
	 * 删除tag数据bytagNum
	 * 
	 * @param tagNum
	 */
	public void deleteTagInfoByTagNum(Integer tagNum);
	
	/**
	 * 更新表中的数据
	 * @param tagNum
	 * @param name
	 * @param age
	 * @param sex
	 * @param maritalStatus
	 */
	public void updateTagInformation(String tagNum, String name, Integer age, String sex, String maritalStatus);
}
