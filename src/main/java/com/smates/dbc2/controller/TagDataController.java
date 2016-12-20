package com.smates.dbc2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.DateAndTagnum;
import com.smates.dbc2.po.PieData;
import com.smates.dbc2.service.TagDataService;

/**
 * 关于标签数据的controller，包括定位、轨迹等
 * 
 * @author March
 *
 */
@Controller
public class TagDataController {

	@Autowired
	private TagDataService tagDataService;

	/**
	 * 查询所有标签目前的定位信息，用于首页（名片页）
	 * @param model
	 * @return
	 */
	@RequestMapping("getAllLocation")
	public String getAllLocation(Model model) {
		model.addAttribute("tagLocationData", tagDataService.getAllLocation());
		return "allTagData.ftl";
	}

	/**
	 * 查询某人某天的信息，用于画饼图
	 * 
	 * @param date
	 * @param tagNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPieData")
	public List<PieData> getPieData(String date, String tagNum) {
		return tagDataService.getPieData(new DateAndTagnum(date, tagNum));
	}

	@RequestMapping("jumpToOneLocation")
	public String jumpToOneLocation(String tagNum, Model model) {
		model.addAttribute("tagNum", tagNum);
		return "onelocation.ftl";
	}
}
