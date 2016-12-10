package com.smates.dbc2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smates.dbc2.service.TagDataService;

@Controller
public class TagDataController {

	@Autowired
	private TagDataService tagDataService;

	@RequestMapping("getAllLocation")
	public String getAllLocation(Model model) {
		model.addAttribute("tagLocationData", tagDataService.getAllLocation());
		return "allTagData.ftl";
	}
}
