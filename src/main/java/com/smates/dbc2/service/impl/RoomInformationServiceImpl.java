package com.smates.dbc2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.RoomInformationDao;
import com.smates.dbc2.po.RoomInformation;
import com.smates.dbc2.service.RoomInformationService;
import com.smates.dbc2.vo.Page;

@Service
public class RoomInformationServiceImpl implements RoomInformationService {
	@Autowired
	private RoomInformationDao roomInformationDao;

	@Override
	public List<RoomInformation> getAllUsefulTag(int pageNo, int pageSize) {
		return roomInformationDao.getAllUsefulTag(new Page(pageNo, pageSize));
	}

	@Override
	public int countSum() {
		return roomInformationDao.countSum();
	}

}
