package com.smates.dbc2.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smates.dbc2.po.TagInformation;
import com.smates.dbc2.qniu.QniuHelper;
import com.smates.dbc2.service.TagInforService;
import com.smates.dbc2.utils.StringUtils;
import com.smates.dbc2.utils.ValidaterUtil;
import com.smates.dbc2.vo.BaseMsg;
import com.smates.dbc2.vo.DataGrideRow;
/**
 * @author 刘晓庆
 */
@Controller
public class TagInfoController {
	
	private Logger logger = Logger.getLogger(TagInfoController.class);

	@Autowired
	private TagInforService tagInforService;
	@Autowired
	private QniuHelper qniuHelper;

	@ResponseBody
	@RequestMapping(value = "getTagByTagNum", method = RequestMethod.GET)
	public DataGrideRow<TagInformation> getTagByTagNum(@RequestParam(defaultValue = "1") int page, int rows) {
		List<TagInformation> tagInfor = tagInforService.getAllUsefulTag(page, rows);
		return new DataGrideRow<TagInformation>(tagInforService.countSum(), tagInfor);
	}

	@ResponseBody
	@RequestMapping(value = "addTagInfoByTagNum", method = RequestMethod.POST)
	public BaseMsg addTagInfoByTagNum(MultipartFile image, String tagNum, String name, Integer age, String sex,
			String maritalStatus, String health) {
		//用户名的正则验证
		if (!ValidaterUtil.checkAccountNumber(name)) {
			return new BaseMsg(false, "用户名格式错误");
		}
		//照片的正则验证
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			if (!ValidaterUtil.checkImage(image.getOriginalFilename())) {
				return new BaseMsg(false, "图片格式只支持png,jpg,bmp,jpeg,gif");
			}
		}

		TagInformation TagInforNew = new TagInformation(tagNum, name, age, sex, maritalStatus, "true", health);

		// 添加用户头像
		String imageName = null;
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			imageName = StringUtils.formateFileName(image.getOriginalFilename());
			TagInforNew.setImage(qniuHelper.formateUserHeadIcon(imageName));
			logger.info("数据库已经存储");
			qniuHelper.uploadFile(image, imageName);// 上传到七牛云中
			logger.info("七牛云已经存储");
		}
		tagInforService.addTagInfoByTagNum(TagInforNew);
		return new BaseMsg(true, "添加成功");
	}

	@ResponseBody
	@RequestMapping(value = "deleteTagInfoByTagNum", method = RequestMethod.POST)
	public BaseMsg deleteTagInfoByTagNum(Integer tagNum) {
		tagInforService.deleteTagInfoByTagNum(tagNum);
		return new BaseMsg(true, "删除成功");
	}

	// @ResponseBody
	// @RequestMapping(value = "updateTagInformation")
	// public BaseMsg updateTagInformation(String tagNum, String name, Integer
	// age, String sex, String maritalStatus) {
	// tagInforService.updateTagInformation(tagNum, name, age, sex,
	// maritalStatus);
	// return new BaseMsg(true, "更新成功");
	// }
}
