package com.yfk.model;

public enum PermissionType {
	URL('U'), 
	SUPPLIER('S'),
	PLANT('P');

	public char type() {
		return type;
	}

	private final char type;

	private PermissionType(char type) {
		this.type = type;
	}
}