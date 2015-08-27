package com.yfk.service.impl;

import com.yfk.dao.MenuDao;
import com.yfk.model.Menu;
import com.yfk.service.UserExistsException;
import com.yfk.service.MenuManager;
import com.yfk.service.MenuService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("menuManager")
@WebService(serviceName = "MenuService", endpointInterface = "com.yfk.service.MenuService")
public class MenuManagerImpl extends GenericManagerImpl<Menu, String> implements MenuManager, MenuService {
    private MenuDao menuDao;
    
    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.dao = menuDao;
        this.menuDao = menuDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Menu> getMenus() {
        return menuDao.getAll();
    }

}
