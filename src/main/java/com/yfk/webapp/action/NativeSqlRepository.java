package com.yfk.webapp.action;

public class NativeSqlRepository {
	public static final String SELECT_ROLE_ASSIGNED_PERMISSION_STATEMENT = "select permission_code from role_permission where permission_type = ? and role_code = ?";
	public static final String SELECT_ROLE_AVAILABLE_PERMISSION_STATEMENT = "select * from permission where type = ?";
}
