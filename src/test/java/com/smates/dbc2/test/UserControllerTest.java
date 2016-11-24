package com.smates.dbc2.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smates.dbc2.controller.UserController;

public class UserControllerTest {
	
	private static UserController userController;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void Before(){
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
		userController = applicationContext.getBean(UserController.class);
	}

	@Test
	public void testCreateUser(){
		userController.createUser(null, 30,"tangng3333", "汤士龙", "123456", "834848102@qq.com", 1, "可用");
	}
	
}


