package com.yfk.model;

import java.util.List;

public class UserMenu extends BaseObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2556637617544139689L;
	
	private String code;		// required
	private String name;
	private String url;
	private Integer sequence;
	private List<UserMenu> childUserMenu;
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}

	public Integer getSequence() {
		return sequence;
	}
	
	public List<UserMenu> getChildUserMenu() {
		return childUserMenu;
	}

	public void setChildUserMenu(List<UserMenu> childUserMenu) {
		this.childUserMenu = childUserMenu;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public String toString() {
		return "UserMenu [code=" + code + "]";
	}


}
