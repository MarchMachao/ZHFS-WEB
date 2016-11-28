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
import com.smates.dbc2.vo.DataGrideRow;

@Controller
public class RoomInformationController {
	@Autowired
	private RoomInformationService roomInformationService;

	@ResponseBody
	@RequestMapping(value = "getroomInfoNum", method = RequestMethod.GET)
	public DataGrideRow<RoomInformation> getAllUsefulTag(@RequestParam(defaultValue = "1") int page, int rows) {
		List<RoomInformation> roomInformation = roomInformationService.getAllUsefulTag(page, rows);
		return new DataGrideRow<>(roomInformationService.countSum(), roomInformation);
	}

}
