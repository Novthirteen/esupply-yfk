package com.yfk.webapp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.PermissionsAdapter;

public class SimplePermissionsAdapter implements PermissionsAdapter {
	@Override
	public boolean isAllowed(MenuComponent menu) {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {
			for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities()) {
				if (grantedAuthority.getAuthority() != null
						&& grantedAuthority.getAuthority().equals(menu.getAction())) {
					return true;
				}
			}
		}
		return false;
	}

}
