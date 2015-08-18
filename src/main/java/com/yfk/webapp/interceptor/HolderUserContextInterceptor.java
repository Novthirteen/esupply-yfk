package com.yfk.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yfk.webapp.util.UserContextHolder;

public class HolderUserContextInterceptor implements Interceptor {
	/**
	* 
	*/
	private static final long serialVersionUID = 1624274593451516344L;

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserContextHolder.Set(request.getRemoteUser());
		return invocation.invoke();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}
}
