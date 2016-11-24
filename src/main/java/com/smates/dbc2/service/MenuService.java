package com.smates.dbc2.service;

import java.util.List;

import com.smates.dbc2.po.Menu;
import com.smates.dbc2.vo.ComboBoxRow;
import com.smates.dbc2.vo.MenuCheckboxVo;

/**
 * menu相关service
 * @author baijw12
 *
 */
public interface MenuService {
	
	/**
	 * 根据用户权限获取获取菜单,二级菜单在一级菜单下
	 * @return
	 */
	public List<Menu> getMenuByRoles(Integer role);
	
	/**
	 * 添加一个菜单
	 * @param menuName 菜单名称
	 * @param parentId	所属一级菜单
	 * @param menuUrl 菜单对应url
	 * @param order 菜单排列位置
	 * @param permition 菜单权限
	 */
	public void addMenu(String menuName, String parentId, String menuUrl, Integer order, String permition);
	
	/**
	 * 获取所有所有一级菜单
	 * @return
	 */
	public List<ComboBoxRow> getParentMenu();
	
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	public List<Menu> getAllMenu(int pageNo, String menuName, String permition, int pageSize);
	
	/**
	 * 获取到menu的总数
	 * @return
	 */
	public int countSum();
	
	/**
	 * 根据menuId删除菜单项
	 * @param menuId
	 */
	public void deleteMenuById(String menuId);
	
	/**
	 * 根据menuId获取menu对象
	 * @param menuId
	 * @return
	 */
	public Menu getMenuById(String menuId);
	
	/**
	 * 更新一个菜单项
	 * @param menuId 菜单id
	 * @param menuName 菜单名称
	 * @param menuUrl 菜单url
	 * @param parentId 父菜单
	 * @param order 菜案序号
	 * @param permition 菜单权限
	 */
	public void updateMenu(String menuId, String menuName, String menuUrl, String parentId, Integer order, String permition);
	
	/**
	 * 转换po格式,让前台checkbox容易识别
	 * @param menu
	 * @return
	 */
	public MenuCheckboxVo formatePo(Menu menu);

}
