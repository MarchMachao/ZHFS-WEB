package com.smates.dbc2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.RoomLocationDao;
import com.smates.dbc2.po.RoomLocation;
import com.smates.dbc2.service.RoomLocationService;

@Service
public class RoomLocationServiceImpl implements RoomLocationService {
	@Autowired
	private RoomLocationDao roomLocationDao;

	@Override
	public List<RoomLocation> getRoomLocation() {
		return roomLocationDao.getRoomLocation();
	}

}
