package com.smates.dbc2.vo;

public class ParentMenuVo {
	private String menuId;
	private String menuName;

	public ParentMenuVo(String menuId, String menuName) {
		this.menuId = menuId;
		this.menuName = menuName;
	}

	public String getParentId() {
		return menuId;
	}

	public void setParentId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
