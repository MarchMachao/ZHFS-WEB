package com.smates.dbc2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.TagDataDao;
import com.smates.dbc2.po.TagLocationData;
import com.smates.dbc2.service.TagDataService;

@Service
public class TagDataSercieImpl implements TagDataService {

	@Autowired
	private TagDataDao tagDataDao;

	@Override
	public List<TagLocationData> getAllLocation() {
		return tagDataDao.getAllLocation();
	}

}
