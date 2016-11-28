package com.smates.dbc2.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.Role;

@Controller
public class RoleController extends BaseController{
	
	public Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping("getAllRole")
	@ResponseBody
	public List<Role> getAllRole(){
		logger.info("获取角色列表");
		return roleService.getAllRoles();
	}
	
}
