package com.smates.dbc2.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.RoomLocation;
import com.smates.dbc2.service.RoomLocationService;
/**
 * 
 * @author 刘晓庆
 *
 */
@Controller
public class RoomLocationController {
	private Logger logger = Logger.getLogger(RoomLocationController.class);
	@Autowired
	private RoomLocationService roomLocationService;

	@ResponseBody
	@RequestMapping(value = "getRoomLocation")
	public List<RoomLocation> getRoomLocation() {
		System.out.println(roomLocationService.getRoomLocation());
		return roomLocationService.getRoomLocation();
	}

}
