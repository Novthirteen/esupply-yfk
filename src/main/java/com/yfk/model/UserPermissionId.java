package com.yfk.model;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserPermissionId implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String permissionCode;
	private PermissionType type;
	
     
	public UserPermissionId() {
		
	}
	public UserPermissionId(String username, String permissionCode,PermissionType type) {
		this.username = username;
		this.permissionCode = permissionCode;
		this.type = type;
	}

	
	
	public String getRoleCode() {
		return username;
	}
	public void setRoleCode(String username) {
		this.username = username;
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
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserPermissionId other = (UserPermissionId) obj;
		if (permissionCode == null) {
			if (other.permissionCode != null)
				return false;
		} else if (!permissionCode.equals(other.permissionCode))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RolePermissionId [username=" + username + ", permissionCode=" + permissionCode + ", type=" + type + "]";
	}
	
	
	
}