package com.yfk.service;

import com.yfk.dao.MenuDao;
import com.yfk.model.Menu;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface MenuManager extends GenericManager<Menu, String> {
    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setMenuDao(MenuDao menuDao);

    /**
     * Retrieves a list of all menus.
     * @return List
     */
    List<Menu> getMenus();
}
