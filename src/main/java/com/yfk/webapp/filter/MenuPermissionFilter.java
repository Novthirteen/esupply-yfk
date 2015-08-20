package com.yfk.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.yfk.webapp.security.SimplePermissionsAdapter;

public class MenuPermissionFilter implements Filter {

	private SimplePermissionsAdapter permissionAdapter = new SimplePermissionsAdapter();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute("permissionAdapter", permissionAdapter);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
