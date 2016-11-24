package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.Role;

public interface RoleDao {
	
	/**
	 * 查询所有的role记录
	 * @return
	 */
	public List<Role> getAllRoles();
	
}
