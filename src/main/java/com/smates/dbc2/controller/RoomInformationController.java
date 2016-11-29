package com.smates.dbc2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.RoomInformation;
import com.smates.dbc2.service.RoomInformationService;
import com.smates.dbc2.vo.BaseMsg;
import com.smates.dbc2.vo.DataGrideRow;
/**
 * 
 * @author wrz
 *
 */
@Controller
public class RoomInformationController {
	@Autowired
	private RoomInformationService roomInformationService;

	@ResponseBody
	@RequestMapping(value = "getroomInfoNum", method = RequestMethod.GET)
	public DataGrideRow<RoomInformation> getAllUseRoomInfo(@RequestParam(defaultValue = "1") int page, int rows) {
		List<RoomInformation> roomInformation = roomInformationService.getAllUsefulRoomInfo(page, rows);
		return new DataGrideRow<>(roomInformationService.countSum(), roomInformation);
	}
	
	@ResponseBody
	@RequestMapping(value = "addRoomInfo", method = RequestMethod.POST)
	public BaseMsg addRoomInfo(String roomNum , String roomName , String cpid , String wakeupNum) {
		
		roomInformationService.addRoomInfo(roomNum, roomName, cpid, wakeupNum);
		return new BaseMsg(true, "添加成功");

	}
	@ResponseBody
	@RequestMapping(value="delRoomInfoByRoomId",method=RequestMethod.POST)
	public BaseMsg delRoomInfoByRoomId(Integer roomId) {
		roomInformationService.delRoomInfoByRoomId(roomId);
		return new BaseMsg(true, "删除成功");
	}
	@ResponseBody
	@RequestMapping(value="updRoomInfo")
	public BaseMsg updRoomInfo(String roomNum , String roomName , String cpid , String wakeupNum) {
		//roomInformationService.updRoomInfo(roomNum, roomName, cpid, wakeupNum);
		return new BaseMsg(true,"修改成功");
	}
}