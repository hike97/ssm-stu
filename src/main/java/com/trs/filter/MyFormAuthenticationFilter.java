package com.trs.filter;

import com.trs.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2018-12-10 16:24
 * @desc 自定义shiro重定向拦截器
 **/
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {


	@Override
	protected void issueSuccessRedirect (ServletRequest request, ServletResponse response) throws Exception {
		HttpSession session = null;
		//从shiro的session中获取User
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated ()){
			//获取身份信息
			User user = (User) subject.getPrincipal();
			session.setAttribute ("loginUser",user);
		}
		WebUtils.issueRedirect(request, response,getSuccessUrl(), null, true);
	}
}
