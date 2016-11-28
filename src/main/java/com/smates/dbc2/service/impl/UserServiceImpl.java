package com.smates.dbc2.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.UserDao;
import com.smates.dbc2.po.User;
import com.smates.dbc2.service.UserService;
import com.smates.dbc2.vo.ComboBoxRow;
import com.smates.dbc2.vo.CostumUser;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userMapper;

	@Override
	public String getCurrentUserActNum() {
		return SecurityUtils.getSubject().getPrincipal().toString();
	}

	@Override
	public User getUserByAccountNumber(String accountNumber) {
		return userMapper.selectByAccountNumber(accountNumber);
	}

	@Override
	public void createUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public int getUserCount() {
		return userMapper.getUserCount();
	}

	@Override
	public List<User> getAllUser(Integer page, Integer rows, String accountNumber, String nickName) {
		CostumUser costumUser = new CostumUser();
		costumUser.setStartCount((page-1)*rows);	
		costumUser.setRows(rows);
		costumUser.setAccountNumber(accountNumber);
		costumUser.setNickName(nickName);
		return userMapper.getAllUser(costumUser);
	}

	@Override
	public void deleteUser(String accountNumber) {
		userMapper.deleteUser(accountNumber);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public List<String> getAllUserName() {
		return userMapper.getAllUserName();
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.getUser();
	}

	@Override
	public List<ComboBoxRow> getUserList() {
		return userMapper.getUserList();
	}

}
