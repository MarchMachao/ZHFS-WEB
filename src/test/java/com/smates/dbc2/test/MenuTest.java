package com.smates.dbc2.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smates.dbc2.controller.MenuController;

public class MenuTest {
	
	private static MenuController menuController;
	
	@BeforeClass
	@SuppressWarnings("resource")
	public static void Before(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
		menuController = applicationContext.getBean(MenuController.class);
	}

	@Test
	public void testMenuController(){
		System.out.println(menuController.getAllMenu(1, null, null,10));
	}
	
}