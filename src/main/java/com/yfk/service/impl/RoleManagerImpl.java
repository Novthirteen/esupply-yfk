package com.yfk.service.impl;

import com.yfk.dao.RoleDao;
import com.yfk.model.Role;
import com.yfk.model.User;
import com.yfk.service.RoleManager;
import com.yfk.webapp.util.PrincipalNullException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of RoleManager interface.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements RoleManager {
    RoleDao roleDao;

    @Autowired
    public RoleManagerImpl(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> getRoles(Role role) {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public Role getRole(String rolename) {
        return roleDao.getRoleByName(rolename);
    }

    /**
     * {@inheritDoc}
     * @throws PrincipalNullException 
     */
    public void saveRole(Role role) throws PrincipalNullException {
        dao.save(role);
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename) {
        roleDao.removeRole(rolename);
    }

	@Override
	public List<Role> search(String searchTerm) {
		// TODO Auto-generated method stub
		  return super.search(searchTerm, Role.class);
	}
	
	
	@Override
	public List<Role> getRoles() {
		  return roleDao.getAllDistinct();
	}

}