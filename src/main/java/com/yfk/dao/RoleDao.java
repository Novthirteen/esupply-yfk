package com.yfk.dao;

import com.yfk.model.Role;

/**
 * Role Data Access Object (DAO) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface RoleDao extends GenericDao<Role, Long> {
    /**
     * Gets role information based on code
     * @param rolename the code
     * @return populated role object
     */
    Role getRoleByCode(String code);

    /**
     * Removes a role from the database by code
     * @param rolename the role's code
     */
    void removeRole(String code);
}
