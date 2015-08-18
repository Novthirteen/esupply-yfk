package com.yfk.model;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RolePermissionId implements Serializable {
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleCode;
	private String permissionCode;
	private PermissionType type;
	
     
	public RolePermissionId() {
		
	}
	public RolePermissionId(String roleCode, String permissionCode,PermissionType type) {
		this.roleCode = roleCode;
		this.permissionCode = permissionCode;
		this.type = type;
	}

	
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	@Enumerated(EnumType.STRING)
	public PermissionType getType() {
		return type;
	}
	public void setType(PermissionType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permissionCode == null) ? 0 : permissionCode.hashCode());
		result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermissionId other = (RolePermissionId) obj;
		if (permissionCode == null) {
			if (other.permissionCode != null)
				return false;
		} else if (!permissionCode.equals(other.permissionCode))
			return false;
		if (roleCode == null) {
			if (other.roleCode != null)
				return false;
		} else if (!roleCode.equals(other.roleCode))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RolePermissionId [roleCode=" + roleCode + ", permissionCode=" + permissionCode + ", type=" + type + "]";
	}
	
	
	
}