package com.yfk.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@IdClass(RolePermissionId.class)
@Table(name = "role_permission")

public class RolePermission implements GrantedAuthority {

		/**
	 * 
	 */
	private static final long serialVersionUID = -3291803526702181083L;
		private String roleCode;			                    // required
	    private String permissionCode;							// required
	    private String permissionType;							// required
	    private String createUser;
	    private Timestamp createDate;
	
	    @Id
	    @Column(name = "role_code",length = 20, nullable = false)
	    public String getRoleCode() {
	        return this.roleCode;
	    }
	    
	    @Id
	    @Column(name = "permission_code",length = 50, nullable = false)
	    public String getPermissionCode() {
	        return this.permissionCode;
	    }
	    
	    @Column(name = "permission_type",length = 1, nullable = false)
	    public String getPermissionType() {
	        return this.permissionType;
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

	    public void setPermissionCode(String permissionCode) {
	        this.permissionCode = permissionCode;
	    }
	    
	    public void setPermissionType(String permissionType) {
	        this.permissionType = permissionType;
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


class RolePermissionId {
	 String roleCode;                   
     String permissionCode;	
}
