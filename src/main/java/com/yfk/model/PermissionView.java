package com.yfk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission_view")
@IdClass(PermissionViewId.class)
public class PermissionView extends BaseObject implements Serializable, GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5827332571609424789L;

	private String username;
	private String permissionCode;
	private String permissionType;

	@Id
	@Column(name = "username", insertable = false, updatable = false)
	public String getUserName() {
		return username;
	}

	@Id
	@Column(name = "permission_code", insertable = false, updatable = false)
	public String getPermissionCode() {
		return permissionCode;
	}

	@Id
	@Column(name = "permission_type", insertable = false, updatable = false)
	public String getPermissionType() {
		return permissionType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	@Override
	public String getAuthority() {
		return permissionCode;
	}

	@Override
	public String toString() {
		return "PermissionView [username=" + username + ", permissionCode=" + permissionCode + ", permissionType="
				+ permissionType + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissionView other = (PermissionView) obj;
		if (permissionCode == null) {
			if (other.permissionCode != null)
				return false;
		} else if (!permissionCode.equals(other.permissionCode))
			return false;
		if (permissionType == null) {
			if (other.permissionType != null)
				return false;
		} else if (!permissionType.equals(other.permissionType))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permissionCode == null) ? 0 : permissionCode.hashCode());
		result = prime * result + ((permissionType == null) ? 0 : permissionType.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
}

class PermissionViewId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2717489380264010916L;
	
	String username;
	String permissionCode;
	String permissionType;
}
