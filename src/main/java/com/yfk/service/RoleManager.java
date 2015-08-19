package com.yfk.service;

import com.yfk.model.Role;
import com.yfk.model.User;
import com.yfk.webapp.util.PrincipalNullException;

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
    Role getRole(String code);

    /**
     * {@inheritDoc}
     * @throws PrincipalNullException 
     */
    void saveRole(Role role) throws RoleExistsException, PrincipalNullException;

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
    
    
    List<Role> search(String searchTerm);
    
    List<Role> getRoles();
}
