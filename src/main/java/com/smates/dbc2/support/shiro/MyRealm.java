package com.smates.dbc2.support.shiro;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.smates.dbc2.po.User;
import com.smates.dbc2.service.UserService;
import com.smates.dbc2.utils.SysConst;

/**
 * 用shiro进行权限管理
 * 
 * @author baijw12
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 登录验证
	 * 
	 * @param token
	 *            用户登录时的账号密码组成的token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		Object principal = token.getPrincipal();
		String accountNumber = token.getPrincipal().toString();
		String credentials = userService.getUserByAccountNumber(accountNumber).getPassword();// 根据登录accountNumber去数据库中查找密码
		String realmName = getName();
		String source = SysConst.SALTSOURCE;
		ByteSource credentialsSalt = new Md5Hash(source);
		// 密码比对过程由shiro自己完成
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt,
				realmName);
		return info;
	}

	/**
	 * 用户授予权限
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String accountNumber = (String) principalCollection.getPrimaryPrincipal();
		User user = userService.getUserByAccountNumber(accountNumber);
		if (user != null) {// 给用户添加角色限制
			info.addRole(user.getRole().toString());
		} else {
			SecurityUtils.getSubject().logout();
		}
		return info;
	}

	/**
	 * 用户的登录的明文密码,先经过md5加密再和数据库比对
	 */
	@PostConstruct
	public void setCredentialMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		setCredentialsMatcher(credentialsMatcher);
	}

}