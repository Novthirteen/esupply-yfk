package com.yfk.webapp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import com.yfk.webapp.security.SimplePermissionsAdapter;

public class MenuPermissionListener implements ServletRequestListener {

	private SimplePermissionsAdapter permissionAdapter = new SimplePermissionsAdapter();
	
	@Override
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
		request.setAttribute("permissionAdapter", permissionAdapter);
	}
}
