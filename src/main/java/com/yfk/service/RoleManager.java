package com.yfk.service;

import com.yfk.model.Role;
import com.yfk.model.User;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleManager extends GenericManager<Role, Long> {
    /**
     * {@inheritDoc}
     */
    List getRoles(Role role);

    /**
     * {@inheritDoc}
     */
    Role getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    void saveRole(Role role) throws RoleExistsException;

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
    
    
    List<Role> search(String searchTerm);
    
    List<Role> getRoles();
}
