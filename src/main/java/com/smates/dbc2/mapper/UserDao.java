package com.smates.dbc2.mapper;

import java.util.List;

import com.smates.dbc2.po.User;
import com.smates.dbc2.vo.ComboBoxRow;
import com.smates.dbc2.vo.CostumUser;

public interface UserDao {
	/**
	 * 根据 accountNumber查找user
	 * @param accountNumber
	 * @return user
	 */
	public User selectByAccountNumber(String accountNumber);

	/**
	 * 插入新用户
	 * @param user
	 * @return
	 */
	public void insertUser(User user);

	/**
	 * 获取所有用户信息
	 * @param rows 
	 * @param page 
	 * @return
	 */
	public List<User> getAllUser(CostumUser costumUser);

	/**
	 * 获取用户数量
	 * @return
	 */
	public int getUserCount();

	/**
	 * 根据acountNumber和nickname查询User
	 * @param user
	 * @return
	 */
	public List<User> searchUser(User user);

	/**
	 * 删除user
	 * @param accountNumber
	 */
	public void deleteUser(String accountNumber);

	/**
	 * 根据用户id更新用户信息
	 * @param user2
	 */
	public void updateUser(User user2);

	/**
	 * 根据id查找user
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id);

	/**
	 * 获取所有用户名称
	 * 
	 * @return
	 */
	public List<String> getAllUserName();

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public List<User> getUser();

	/**
	 * 获取所有用户id和nickname
	 * 
	 * @return
	 */
	public List<ComboBoxRow> getUserList();
	
}