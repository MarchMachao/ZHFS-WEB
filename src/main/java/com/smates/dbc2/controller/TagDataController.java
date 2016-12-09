package com.smates.dbc2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.TagLocationData;
import com.smates.dbc2.service.TagDataService;

@Controller
public class TagDataController {

	@Autowired
	private TagDataService tagDataService;

	@ResponseBody
	@RequestMapping("getAllLocation")
	public List<TagLocationData> getAllLocation() {
		return tagDataService.getAllLocation();
	}
}
