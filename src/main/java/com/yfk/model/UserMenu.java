package com.yfk.model;

import java.util.List;

public class UserMenu extends BaseObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2556637617544139689L;
	
	private String value;		// required
	private String text;
	private String url;
	private String imageUrl;
	private Integer sequence;
	private boolean isPathUpdate;
	private List<UserMenu> items;
	
	public String getValue() {
		return value;
	}

	public String getText() {
		return text;
	}
	
	public String getUrl() {
		return url;
	}

	public Integer getSequence() {
		return sequence;
	}
	
	public List<UserMenu> getItems() {
		return items;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public void setItems(List<UserMenu> items) {
		this.items = items;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void setText(String text) {
		this.text = text;
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
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public String toString() {
		return "UserMenu [value=" + value + "]";
	}

	public boolean isPathUpdate() {
		return isPathUpdate;
	}

	public void setPathUpdate(boolean isPathUpdate) {
		this.isPathUpdate = isPathUpdate;
	}




}
