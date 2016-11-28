package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.Menu;
import com.smates.dbc2.vo.ComboBoxRow;
import com.smates.dbc2.vo.CostumMenu;

public interface MenuDao {
	
	/**
	 * 获取所有菜单,一级菜单和二级菜单
	 * @return
	 */
	public List<Menu> getMenuByRole(Integer role);
	
	/**
	 * 向s_menu中插入一条数据
	 * @param menu
	 */
	public void addMenu(Menu menu);
	
	/**
	 * 得到所有的一级菜单
	 * @return
	 */
	public List<ComboBoxRow> getParentMenu();
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	public List<Menu> getAllMenu(CostumMenu costumMenu);
	
	/**
	 * 统计menu表中的记录总数
	 * @return
	 */
	public int countSum();
	
	/**
	 * 删除s_menu表中的一条记录
	 * @param menuId
	 */
	public void deleteMenuById(String menuId);
	
	/**
	 * 根据menuId查找menu记录
	 * @return
	 */
	public Menu getMenuById(String menuId);
	
	/**
	 * 更新menu记录
	 * @param menu
	 * @return
	 */
	public void updateMenu(Menu menu);
	
}
