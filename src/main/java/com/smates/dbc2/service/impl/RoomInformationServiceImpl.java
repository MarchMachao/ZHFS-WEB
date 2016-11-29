package com.smates.dbc2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.RoomInformationDao;
import com.smates.dbc2.po.RoomInformation;
import com.smates.dbc2.service.RoomInformationService;
import com.smates.dbc2.vo.Page;
/**
 * 
 * @author wrz
 *
 */
@Service
public class RoomInformationServiceImpl implements RoomInformationService {
	@Autowired
	private RoomInformationDao roomInformationDao;
	
	@Override
	public List<RoomInformation> getAllUsefulRoomInfo(int pageNo, int pageSize) {
		return roomInformationDao.getAllUsefulRoom(new Page(pageNo, pageSize));
	}

	@Override
	public int countSum() {
		return roomInformationDao.countSum();
	}

	@Override
	public void addRoomInfo(String roomNum, String roomName, String cpid, String wakeupNum) {
		roomInformationDao.addRoomInfo(new RoomInformation(roomNum,roomName,cpid,wakeupNum));
		
	}

	@Override
	public void delRoomInfoByRoomId(Integer roomId) {
		roomInformationDao.delRoomInfoByRoomId(roomId);
	}

	@Override
	public void updRoomInfo(Integer roomId , String roomNum, String roomName, String cpid, String wakeupNum) {
		
		//roomInformationDao.updRoomInfo(roomNum , roomName , cpid , wakeupNum);		
	}

}
