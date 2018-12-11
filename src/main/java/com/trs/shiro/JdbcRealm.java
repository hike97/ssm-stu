package com.trs.shiro;

import com.trs.model.User;
import com.trs.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2018-12-07 15:40
 * @desc shiro realm验证
 **/
public class JdbcRealm extends AuthorizingRealm{

	private static final Logger log = LoggerFactory.getLogger(JdbcRealm.class);

	@Autowired
	private UserService userService;

	//授权操作
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principals) {
		//1. 从 PrincipalCollection 中来获取登录用户的信息
		User principal = (User) principals.getPrimaryPrincipal();

		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<> ();
		roles.add("user");
		if("admin".equals(principal.getUserName ())){
			roles.add("admin"); //admin可以看全部
		}

		//3. 创建 SimpleAuthorizationInfo, 并设置其 roles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

		//4. 返回 SimpleAuthorizationInfo 对象.
		return info;
	}
	//认证操作
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken token) throws AuthenticationException {
		System.out.println("[FirstRealm] doGetAuthenticationInfo");

		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();

		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		User user = userService.selectByUserName (username);
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if(StringUtils.isEmpty (user)){
			throw new UnknownAccountException ("用户不存在!");
		}
		//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = user;
		//2). credentials: 密码.
		Object credentials = user.getPassWord ();
		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值.
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);

		SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		log.info ("info的内容，{}",info);
		return info;
	}
}
