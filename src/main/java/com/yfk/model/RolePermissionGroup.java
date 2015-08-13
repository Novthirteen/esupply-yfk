package com.yfk.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@IdClass(RolePermissionGroupId.class)
@Table(name = "role_permission_group")

public class RolePermissionGroup implements GrantedAuthority {


		/**
	 * 
	 */
	private static final long serialVersionUID = -1799221237122011806L;
		private String roleCode;			                    // required
	    private String permissionGroupCode;							// required
	    private String createUser;
	    private Timestamp createDate;
	
	    @Id
	    @Column(name = "role_code",length = 20, nullable = false)
	    public String getRoleCode() {
	        return this.roleCode;
	    }
	    
	    @Id
	    @Column(name = "permission_group_code",length = 20, nullable = false)
	    public String getPermissionGroupCode() {
	        return this.permissionGroupCode;
	    }

	    @Column(name = "create_user", length = 50, nullable = false, updatable = false)
	    public String getCreateUser() {
	        return createUser;
	    }
	    
	    @Column(name = "create_date", nullable = false, updatable = false)
	    public Timestamp getCreateDate() {
	        return createDate;
	    }

	    public void setRoleCode(String roleCode) {
	        this.roleCode = roleCode;
	    }

	    public void setPermissionGroupCode(String permissionGroupCode) {
	        this.permissionGroupCode = permissionGroupCode;
	    }
	   	  
	    public void setCreateUser(String createUser) {
	        this.createUser = createUser;
	    }
	    
	    public void setCreateDate(Timestamp createDate) {
	        this.createDate = createDate;
	    }
	    
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}


class RolePermissionGroupId {
	 String roleCode;                   
     String permissionGroupCode;	
}
