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
		List<TagLocationData> locationDatas = tagDataDao.getAllLocation();
		for (int i = 0; i < locationDatas.size(); i++) {
			try {
				if (!locationDatas.get(i).getEnd().equals("未离开")) {
					locationDatas.get(i).setRoomName("不在范围内");// 如果end有数据，说明该标签已经从该房间离开
				}

			} catch (NullPointerException e) {
				locationDatas.get(i).setRoomName("新用户，暂无数据");
			}
		}
		return locationDatas;
	}

}
