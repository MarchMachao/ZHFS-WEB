package com.smates.dbc2.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smates.dbc2.po.User;
import com.smates.dbc2.utils.ShiroUtils;
import com.smates.dbc2.utils.StringUtils;
import com.smates.dbc2.utils.SysConst;
import com.smates.dbc2.utils.ValidaterUtil;
import com.smates.dbc2.vo.BaseMsg;
import com.smates.dbc2.vo.ComboBoxRow;
import com.smates.dbc2.vo.DataGrideRow;

/**
 * 用户相关的请求
 * 
 * @author tangShilong
 *
 */
@Controller
public class UserController extends BaseController {
	private static Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 返回登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "Login.ftl";
	}

	/**
	 * 处理登录请求
	 * 
	 * @param userid
	 *            用户登录的accountNumber
	 * @param userpwd
	 *            用户登录的明文密码
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String submit(ModelMap modelMap, String accountNumber, String userpwd) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(accountNumber, userpwd);
		try {
			subject.login(token);
		} catch (AuthenticationException ae) {
			logger.info("账号或密码错误");
			modelMap.addAttribute("callback", "false");
			return "Login.ftl";
		}
		logger.info("登录成功");
		return "redirect:home.do";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register() {
		return "register.ftl";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String Register(ModelMap modelMap, String accountNumber, String nickname, String password, String repwd,
			String email) {
		if (!password.equals(repwd)) {
			logger.info(password + "+" + repwd);;
			modelMap.addAttribute("callback", "Two passwords are not consistent !");
			callback(modelMap, accountNumber, nickname, password, repwd, email);
			return "register.ftl";
		}
		User user = userService.getUserByAccountNumber(accountNumber);
		if (user != null) {
		    modelMap.addAttribute("callback", "username is already exit !");
			callback(modelMap, accountNumber, nickname, password, repwd, email);
			return "register.ftl";
		}
		if (!ValidaterUtil.checkAccountNumber(accountNumber)) {//1-15位大小写字母和数字
			logger.info("账号格式错误");
		    modelMap.addAttribute("callback", "accountNumber can't be used !(^[A-Za-z0-9]{1,15}$)");
			callback(modelMap, accountNumber, nickname, password, repwd, email);
			return "register.ftl";
		}
		if (!ValidaterUtil.checkPassWord(password)) {//6-40位大小写 字母数字 .!的组合
			logger.info("密码格式错误");
			modelMap.addAttribute("callback", "password can't be used !(^[A-Za-z0-9!.]{6,40}$)");
			callback(modelMap, accountNumber, nickname, password, repwd, email);
			return "register.ftl";
		}
		if (email != null) {
			if (!ValidaterUtil.checkEMail(email)) {
				logger.info("邮箱格式不正确");
				modelMap.addAttribute("callback", "email can't be used !");
				callback(modelMap, accountNumber, nickname, password, repwd, email);
				return "register.ftl";
			}
		}

		logger.info("插入用户");
		User user2 = new User();
		user2.setAccountNumber(accountNumber);
		user2.setCreateDate(new Date());
		user2.seteMail(email);
		user2.setNickName(nickname);
		user2.setPassword(ShiroUtils.passwdMD5(password));
		user2.setRole(0);
		userService.createUser(user2);

		modelMap.addAttribute("callback", "create user seccess !");
		return "redirect:login.do";

	}

	/**
	 * 注册返回值
	 * 
	 * @param modelMap
	 * @param accountNumber
	 * @param nickname
	 * @param password
	 * @param repwd
	 * @param email
	 */
	private void callback(ModelMap modelMap, String accountNumber, String nickname, String password, String repwd,
			String email) {
		modelMap.addAttribute("accountNumber", accountNumber);
		modelMap.addAttribute("nickname", nickname);
		modelMap.addAttribute("password", password);
		modelMap.addAttribute("repwd", repwd);
		modelMap.addAttribute("email", email);
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "Login.ftl";
	}

	/**
	 * 登录成功后,显示主界面,根据用户不同的权限,显示不同的菜单
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("home")
	public String home(ModelMap modelMap) {
		modelMap.addAttribute("menulist", menuService
				.getMenuByRoles(userService.getUserByAccountNumber(userService.getCurrentUserActNum()).getRole()));
		modelMap.addAttribute("userName",
				userService.getUserByAccountNumber(userService.getCurrentUserActNum()).getNickName());
		return "Home.ftl";
	}

	/**
	 * 创建用户,id为null是为新增用户,id不为空时为修改用户
	 * 
	 * @param accountNumber
	 * @param nickName
	 * @param password
	 * @param eMail
	 * @return 创建是否成功
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "admin/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public BaseMsg createUser(MultipartFile image, Integer id, String accountNumber, String nickName, String password,
			String eMail, Integer role, String enable) {

		if (!ValidaterUtil.checkAccountNumber(accountNumber)) {
			logger.info("账号格式错误");
			return new BaseMsg(false, "wrong accountNumber");
		}
		if (!ValidaterUtil.checkPassWord(password)) {
			logger.info("密码格式错误");
			return new BaseMsg(false, "password wrong");
		}
		if (eMail != null) {
			if (!ValidaterUtil.checkEMail(eMail)) {
				logger.info("邮箱格式不正确");
				return new BaseMsg(false, "wrong e-mail");
			}
		}
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			if (!ValidaterUtil.checkImage(image.getOriginalFilename())) {
				logger.info("上传的不是图片");
				return new BaseMsg(false, "图片格式只支持png,jpg,bmp,jpeg,gif");
			}
		}

		// 创建user对象,默认头像
		User user = new User(id, accountNumber, nickName, ShiroUtils.passwdMD5(password), role, new Date(), eMail);

		User userPo = userService.getUserByAccountNumber(accountNumber);

		// 加入图片样式,缩放和大小
		String imageName = null;
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			imageName = StringUtils.formateFileName(image.getOriginalFilename());
			user.setImage(qniuHelper.formateUserHeadIcon(imageName));
		}

		if (id == null) {
			logger.info("add user");
			if (userPo != null) {
				logger.info("账号已存在，注册失败");
				return new BaseMsg(false, "accountNumber already exist");
			}
			userService.createUser(user);
		} else {
			// 判断用户是否修改了密码
			String popwd = userPo.getPassword();
			if (popwd.equals(password)) {
				user.setPassword(password);
			}
			// 如果用户没有修改头像
			if (StringUtils.isEmpty(image.getOriginalFilename())) {
				user.setImage(null);
			}
			logger.info("update user");
			userService.updateUser(user);
		}

		// 如果用户上传了头像,则使用用户上传的头像
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			qniuHelper.uploadFile(image, imageName);
		}
		return new BaseMsg(true, "保存成功");
	}

	/**
	 * 获取指定页数所有用户信息,返回用户总数
	 * 
	 * @return
	 */
	@RequestMapping(value = "admin/getAllUser", method = RequestMethod.GET)
	@ResponseBody
	public DataGrideRow<User> getAllUser(Integer page, Integer rows, String accountNumber, String nickName) {
		logger.info("根据页数行数获取用户信息");
		List<User> list = userService.getAllUser(page, rows, accountNumber, nickName);
		int total = userService.getUserCount();
		return new DataGrideRow<User>(total, list);
	}

	/**
	 * 根据accountNumber删除用户
	 * 
	 * @param accountNumber
	 * @return
	 */
	@RequestMapping(value = "admin/deleteUser", method = RequestMethod.GET)
	@ResponseBody
	public BaseMsg deleteUser(String accountNumber) {
		userService.deleteUser(accountNumber);
		logger.info("user删除成功");
		return new BaseMsg(true, "删除成功");
	}

	/**
	 * 根据accountNumber查找用户
	 * 
	 * @param accountNumber
	 * @return
	 */
	@RequestMapping(value = "admin/getUserByAccountNumber", method = RequestMethod.GET)
	@ResponseBody
	public User getUserByAccountNumber(String accountNumber) {
		return userService.getUserByAccountNumber(accountNumber);
	}

	/**
	 * 获取所有用户id和nickname
	 * 
	 * @return
	 */
	@RequestMapping(value = "getUserList", method = RequestMethod.POST)
	@ResponseBody
	public List<ComboBoxRow> getUserList(){
		logger.info("获取所有用户id和nickname");
		List<ComboBoxRow> comboBoxRows = userService.getUserList();
		comboBoxRows.add(new ComboBoxRow("0", "无"));// 添加无上级菜单选项
		return comboBoxRows;
	}
	
	/**
	 * 获取当前登录的用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCurrentUser", method = RequestMethod.GET)
	@ResponseBody
	public User getCurrentUser() {
		return userService.getUserByAccountNumber(userService.getCurrentUserActNum());
	}

	/**
	 * 普通用户权限下对信息的修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	public BaseMsg updateUser(MultipartFile image, Integer id, String accountNumber, String nickName, String password,
			String eMail) {
		logger.info("用户自身信息维护");
		if (!ValidaterUtil.checkPassWord(password)) {
			logger.info("密码格式错误");
			return new BaseMsg(false, "password wrong");
		}
		if (eMail != null) {
			if (!ValidaterUtil.checkEMail(eMail)) {
				logger.info("邮箱格式不正确");
				return new BaseMsg(false, "wrong e-mail");
			}
		}
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			if (!ValidaterUtil.checkImage(image.getOriginalFilename())) {
				logger.info("上传的不是图片");
				return new BaseMsg(false, "图片格式只支持png,jpg,bmp,jpeg,gif");
			}
		}

		// 用户权限下只允许修改,密码,邮箱,昵称和头像
		String fileName = null;
		User user = new User(id, null, nickName, ShiroUtils.passwdMD5(password), null, null, eMail);
		User userpo = userService.getUserByAccountNumber(userService.getCurrentUserActNum());
		// 密码没有修改
		if (password.equals(userpo.getPassword())) {
			user.setPassword(null);
		}
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			// 修改头像
			fileName = StringUtils.formateFileName(image.getOriginalFilename());
			user.setImage(qniuHelper.formateUserHeadIcon(fileName));
		} else {
			// 不修改头像
			user.setImage(null);
		}
		userService.updateUser(user);
		if (!StringUtils.isEmpty(image.getOriginalFilename())) {
			qniuHelper.uploadFile(image, fileName);
		}
		return new BaseMsg(true, "信息修改成功");
	}
}