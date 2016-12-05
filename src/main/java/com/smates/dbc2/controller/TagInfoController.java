package com.smates.dbc2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.TagInformation;
import com.smates.dbc2.service.TagInforService;
import com.smates.dbc2.vo.BaseMsg;
import com.smates.dbc2.vo.DataGrideRow;

@Controller
public class TagInfoController {

	@Autowired
	private TagInforService tagInforService;
	
	@ResponseBody
	@RequestMapping(value="getTagByTagNum", method = RequestMethod.GET)
	public DataGrideRow<TagInformation> getTagByTagNum(@RequestParam(defaultValue = "1") int page, int rows) {
		List<TagInformation> tagInfor = tagInforService.getAllUsefulTag(page, rows);
		return new DataGrideRow<TagInformation>(tagInforService.countSum(), tagInfor);
	}
	
	@ResponseBody
	@RequestMapping(value = "addTagInfoByTagNum", method = RequestMethod.POST)
	public BaseMsg addTagInfoByTagNum(String tagNum, String name, Integer age, String sex, String maritalStatus) {
		
		tagInforService.addTagInfoByTagNum(tagNum, name, age, sex, maritalStatus);
		return new BaseMsg(true, "添加成功");
	}
	
	@ResponseBody
	@RequestMapping(value="deleteTagInfoByTagNum",method=RequestMethod.POST)
	public BaseMsg deleteTagInfoByTagNum(Integer tagNum) {
		tagInforService.deleteTagInfoByTagNum(tagNum);
		return new BaseMsg(true, "删除成功");
	}
	
	@ResponseBody
	@RequestMapping(value="updateTagInformation")
	public BaseMsg updateTagInformation(String tagNum ,String name, Integer age, String sex, String maritalStatus) {
		tagInforService.updateTagInformation(tagNum,name, age, sex, maritalStatus);
		return new BaseMsg(true, "更新成功");
	}
}
