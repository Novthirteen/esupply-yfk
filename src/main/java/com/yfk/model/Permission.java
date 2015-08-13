package com.yfk.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority {

	 	/**
	 * 
	 */
	private static final long serialVersionUID = 364433575686941032L;
		private String code;                    // required
	    private String type;                    // required
	    private String name;
	    private String createUser;
	    private Timestamp createDate;
	    private String updateUser;
	    private Timestamp updateDate;
	    
	    
	    
	    @Id
	    @Column(length = 50)
	    public String getCode() {
	        return this.code;
	    }

	    @Column(length = 1)
	    public String getType() {
	        return this.type;
	    }
	    
	    @Column(length = 50)
	    public String getName() {
	        return this.name;
	    }

	    @Column(name = "create_user", length = 50, nullable = false, updatable = false)
	    public String getCreateUser() {
	        return createUser;
	    }
	    
	    @Column(name = "create_date", nullable = false, updatable = false)
	    public Timestamp getCreateDate() {
	        return createDate;
	    }
	    
	    @Column(name = "update_user", length = 50, nullable = false)
	    public String getUpdateUser() {
	        return updateUser;
	    }
	    
	    @Column(name = "update_date", nullable = false)
	    public Timestamp getUpdateDate() {
	        return updateDate;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    public void setType(String type) {
	        this.type = type;
	    }


	    public void setCreateUser(String createUser) {
	        this.createUser = createUser;
	    }
	    
	    public void setCreateDate(Timestamp createDate) {
	        this.createDate = createDate;
	    }
	    
	    public void setUpdateUser(String updateUser) {
	        this.updateUser = updateUser;
	    }
	    
	    public void setUpdateDate(Timestamp updateDate) {
	        this.updateDate = updateDate;
	    }
	    
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}