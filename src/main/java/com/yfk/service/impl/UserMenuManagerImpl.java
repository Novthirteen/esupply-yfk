package com.yfk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.jws.WebService;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import com.yfk.model.UserMenu;
import com.yfk.service.UserMenuService;
import com.yfk.webapp.util.AppContextUtil;
import com.yfk.webapp.util.PrincipalNullException;
import com.yfk.webapp.util.SecurityContextHelper;


@Service("userMenuManager")
@WebService(serviceName = "UserMenuService", endpointInterface = "com.yfk.service.UserMenuService")
public class UserMenuManagerImpl extends TranslateManager implements UserMenuService {
	
	@Override
	public List<UserMenu> getUserMenus() throws PrincipalNullException {
		try
		{
			ServletContext context = AppContextUtil.getServletContext();
			List<UserMenu> userMenus = SecurityContextHelper.getRemoteUserMenu();
			String rootPath = context.getContextPath();
			if(userMenus != null && userMenus.isEmpty() == false)
			{
				for(UserMenu userMenu : userMenus)
				{
					if(userMenu.isPathUpdate() == false)
					{
						if(!userMenu.getUrl().isEmpty())
						{
							userMenu.setUrl(rootPath+userMenu.getUrl());
						}
						userMenu.setText(getText(userMenu.getText()));
						userMenu.setPathUpdate(true);
					}
					if(userMenu.getItems()!=null && !userMenu.getItems().isEmpty())
					{
						for(UserMenu item : userMenu.getItems())
						{
							if(item.isPathUpdate() == false)
							{
								if(!item.getUrl().isEmpty())
								{
									item.setUrl(rootPath+item.getUrl());
								}
								item.setText(getText(item.getText()));
								item.setPathUpdate(true);
							}
							
						}
					}
				}
			}
			return userMenus;
		}
		catch (PrincipalNullException e) {
            return new ArrayList<UserMenu>();
        } 
	}

	
	
}
