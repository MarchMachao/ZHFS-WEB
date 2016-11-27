package com.smates.dbc2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.TagInformationDao;
import com.smates.dbc2.po.TagInformation;
import com.smates.dbc2.service.TagInforService;
import com.smates.dbc2.vo.Page;

@Service
public class TagInforServiceImpl implements TagInforService {
	
	@Autowired
	private TagInformationDao tagInformationDao;

	@Override
	public List<TagInformation> getAllUsefulTag(int pageNo, int pageSize) {
		return tagInformationDao.getAllUsefulTag(new Page(pageNo, pageSize));
	}

	@Override
	public void addTagInfoByTagNum(String tagNum, String name, Integer age, String sex, String maritalStatus) {
		tagInformationDao.addTagInfoByTagNum(new TagInformation(tagNum, name, age, sex, maritalStatus, "true"));
	}

	@Override
	public void deleteTagInfoByTagNum(Integer tagNum) {
		tagInformationDao.deleteTagInfoByTagNum(tagNum);
	}

	@Override
	public void updateTagInformation(TagInformation tagInfo) {
		
	}

	@Override
	public int countSum() {
		return tagInformationDao.countSum();
	}

}
