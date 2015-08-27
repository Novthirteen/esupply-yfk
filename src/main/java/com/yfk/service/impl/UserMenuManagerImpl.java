package com.yfk.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.jws.WebService;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import com.yfk.model.UserMenu;
import com.yfk.service.UserMenuService;
import com.yfk.webapp.util.AppContextUtil;
import com.yfk.webapp.util.PrincipalNullException;
import com.yfk.webapp.util.SecurityContextHelper;

@Service("userMenuManager")
@WebService(serviceName = "UserMenuService", endpointInterface = "com.yfk.service.UserMenuService")
public class UserMenuManagerImpl extends TranslateManager implements UserMenuService {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UserMenu> getUserMenus() throws PrincipalNullException {
		// TODO Auto-generated method stub
		ServletContext context = AppContextUtil.getServletContext();
		List<UserMenu> userMenus = SecurityContextHelper.getRemoteUserMenu();
		String rootPath = context.getContextPath();
		for(UserMenu userMenu : userMenus)
		{
			if(!userMenu.getUrl().isEmpty() && !userMenu.isPathUpdate() == false)
			{
				userMenu.setUrl(rootPath+userMenu.getUrl());
				userMenu.setPathUpdate(true);
			}
			if(userMenu.getItems()!=null && !userMenu.getItems().isEmpty())
			{
				for(UserMenu item : userMenu.getItems())
				{
					if(!item.getUrl().isEmpty() && item.isPathUpdate() == false)
					{
						item.setUrl(rootPath+item.getUrl());
						item.setPathUpdate(true);
					}
				}
			}
			String tt = getText("user.added");
			//userMenu.setText(userMenu.getText());
		}
		return userMenus;
		//return SecurityContextHelper.getRemoteUserMenu();
	}

	
	
}
