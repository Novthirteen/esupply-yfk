package com.yfk.service;

import java.util.List;

import com.yfk.model.PermissionType;
import com.yfk.model.Role;
import com.yfk.webapp.util.PrincipalNullException;

public interface RoleManager extends GenericManager<Role, String> {
	void saveRolePermission(String roleCode, PermissionType permissionType, List<String> assignedPermissions)
			throws PrincipalNullException;
	
	void saveRoleUser(String roleCode, List<String> assignedUsers)
			throws PrincipalNullException;
	
	void deleteRole(String roleCode); 
}
