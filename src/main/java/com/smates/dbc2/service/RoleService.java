package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.Role;

public interface RoleService {
	
	/**
	 * 获取角色列表
	 * @return
	 */
	public List<Role> getAllRoles();
	
}
