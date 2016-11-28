package com.smates.dbc2.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smates.dbc2.po.Menu;
import com.smates.dbc2.utils.StringUtils;
import com.smates.dbc2.vo.BaseMsg;
import com.smates.dbc2.vo.ComboBoxRow;
import com.smates.dbc2.vo.DataGrideRow;
import com.smates.dbc2.vo.MenuCheckboxVo;

@RequestMapping("admin")
@Controller
public class MenuController extends BaseController {

	public Logger logger = Logger.getLogger(MenuController.class);

	/**
	 * 添加和修改菜单项,menuId为空时为新增菜单,menuId不为空时为更新菜单
	 * 
	 * @param menuId
	 *            菜单ID可以为空
	 * 
	 * @param menuName
	 *            菜单名称
	 * @param menuUrl
	 *            菜单url
	 * @param parentId
	 *            父菜单id
	 * @param orderNo
	 *            菜单排序号
	 * @param permition
	 *            菜单权限
	 * @return 是否添加成功,以及反馈信息
	 */
	@RequestMapping(value = "saveMenu", method = RequestMethod.POST)
	@ResponseBody
	public BaseMsg addMenu(String menuId, String menuName, String menuUrl, String parentId, Integer order, String permition) {
		if(StringUtils.isEmpty(menuId)){
			logger.info("添加菜单项");
			menuService.addMenu(menuName, parentId, menuUrl, order, permition);
			return new BaseMsg(true, "菜单添加成功");
		}else{
			logger.info("更新菜单项");
			menuService.updateMenu(menuId, menuName, menuUrl, parentId, order, permition);
			return new BaseMsg(true, "菜单更新成功");
		}
		
	}

	/**
	 * 按条件查询菜单
	 * 
	 * @param page
	 *            当前页数
	 * @param menuName
	 *            菜单名称
	 * @param permition
	 *            菜单权限
	 * @param rows
	 *            每页的记录条数
	 * @return easyUI格式的json
	 */
	@RequestMapping(value = "getAllMenu", method = RequestMethod.GET)
	@ResponseBody
	public DataGrideRow<Menu> getAllMenu(@RequestParam(defaultValue = "1") int page, String menuName, String permition,
			 int rows) {
		logger.info("获取所有菜单项");
		List<Menu> menus = menuService.getAllMenu(page, menuName, permition, rows);
		return new DataGrideRow<Menu>(menuService.countSum(), menus);
	}

	/**
	 * 获取所有一级菜单
	 * 
	 * @return
	 */
	@RequestMapping("getParentMenu")
	@ResponseBody
	public List<ComboBoxRow> getParentMenu() {
		logger.info("获取所有一级菜单");
		List<ComboBoxRow> comboBoxRows = menuService.getParentMenu();
		comboBoxRows.add(new ComboBoxRow("0", "无"));// 添加无上级菜单选项
		return comboBoxRows;
	}

	/**
	 * 删除一个菜单
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping("deleteMenu")
	@ResponseBody
	public BaseMsg deleteMenu(String menuId) {
		logger.info("删除一个菜单,菜单ID:" + menuId);
		menuService.deleteMenuById(menuId);
		return new BaseMsg(true, "删除成功");
	}

	/**
	 * 获取一个菜单
	 * @return
	 */
	@RequestMapping("getMenuById")
	@ResponseBody
	public MenuCheckboxVo getMenuById(String menuId) {
		logger.info("获取一个菜单,menuId:" + menuId);
		Menu menu = menuService.getMenuById(menuId);
		MenuCheckboxVo menuCheckboxVo = menuService.formatePo(menu);
		return menuCheckboxVo;
	}

}
