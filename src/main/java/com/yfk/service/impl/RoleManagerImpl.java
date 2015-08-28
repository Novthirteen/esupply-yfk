package com.yfk.service.impl;

import java.util.List;

import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfk.model.PermissionType;
import com.yfk.model.Role;
import com.yfk.model.RolePermission;
import com.yfk.service.RoleManager;
import com.yfk.service.UniversalManager;
import com.yfk.webapp.util.PrincipalNullException;

@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, String>implements RoleManager {

	@Autowired
	private UniversalManager universalManager;

	@Override
	public void saveRolePermission(String roleCode, PermissionType permissionType, List<String> assignedPermissions)
			throws PrincipalNullException {
		this.universalManager.executeByHql("delete from RolePermission where roleCode = ? and permissionType = ?",
				new Object[] { roleCode, permissionType });

		if (!CollectionHelper.isEmpty(assignedPermissions)) {
			for (String assignedPermission : assignedPermissions) {
				RolePermission rolePermission = new RolePermission();

				rolePermission.setRoleCode(roleCode);
				rolePermission.setPermissionType(permissionType);
				rolePermission.setPermissionCode(assignedPermission);

				this.universalManager.save(rolePermission);
			}
		}
	}
	
	public void deleteRole(String roleCode) {
		this.universalManager.executeByHql("delete from RolePermission where roleCode = ?",
				new Object[] { roleCode });
		
		this.universalManager.remove(Role.class, roleCode);
	}
}
