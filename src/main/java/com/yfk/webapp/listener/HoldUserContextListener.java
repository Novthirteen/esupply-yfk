package com.yfk.webapp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import com.yfk.webapp.util.UserContextHolder;

public class HoldUserContextListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
		UserContextHolder.Set(request.getRemoteUser());
	}
}
