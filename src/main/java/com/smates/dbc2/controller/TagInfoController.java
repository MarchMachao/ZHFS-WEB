package com.smates.dbc2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.TagInformation;
import com.smates.dbc2.service.TagInforService;

@Controller
public class TagInfoController {

	@Autowired
	private TagInforService tagInforService;
	
	@ResponseBody
	@RequestMapping(value="getTagByTagNum", method = RequestMethod.GET)
	public List<TagInformation> getTagByTagNum() {
		return tagInforService.getAllUsefulTag();
	}
	
	@RequestMapping(value="addTagInfoByTagNum",method = RequestMethod.GET)
	public void addTagInfoByTagNum(String tagNum, String name, Integer age, String sex, String maritalStatus){
		
		//tagInforService.addTagInfoByTagNum("123", "ss", 80, "nan", "sdsd");
		tagInforService.addTagInfoByTagNum(tagNum, name, age, sex, maritalStatus);
	}
	
	@RequestMapping(value="deleteTagInfoByTagNum",method=RequestMethod.POST)
	public void deleteTagInfoByTagNum(Integer tagNum){
		tagInforService.deleteTagInfoByTagNum(tagNum);
	}
}
