package com.yfk.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.util.StringHelper;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import com.yfk.model.LabelValue;
import com.yfk.model.Permission;
import com.yfk.model.PermissionType;
import com.yfk.model.Role;
import com.yfk.service.RoleManager;

/**
 * Action for facilitating Role Management feature.
 */
public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4453515369924540643L;

	private List<Role> roles;
	private Role role;
	private String code;
	private String permissionType;
	private List<LabelValue> availablePermissions;
	private List<String> assignedPermissions;
	private RoleManager roleManager;

	/**
	 * Holder for roles to display on list screen
	 *
	 * @return list of roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<LabelValue> getAvailablePermissions() {
		return availablePermissions;
	}

	public void setAvailablePermissions(List<LabelValue> availablePermissions) {
		this.availablePermissions = availablePermissions;
	}

	public List<String> getAssignedPermissions() {
		return assignedPermissions;
	}

	public void setAssignedPermissions(List<String> assignedPermissions) {
		this.assignedPermissions = assignedPermissions;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public List<LabelValue> getPermissionTypeList() {
		List<LabelValue> permissionTypeList = new ArrayList<LabelValue>();
		permissionTypeList.add(new LabelValue(PermissionType.U.toString(), getText("permission.url")));
		permissionTypeList.add(new LabelValue(PermissionType.S.toString(), getText("permission.supplier")));
		permissionTypeList.add(new LabelValue(PermissionType.P.toString(), getText("permission.plant")));

		return permissionTypeList;
	}

	/**
	 * Delete the role passed in.
	 *
	 * @return success
	 */
	public String delete() {
		try {
			this.roleManager.deleteRole(role.getCode());
			List<Object> args = new ArrayList<Object>();
			args.add(role.getCode());
			saveMessage(getText("role.deleted", args));
		} catch (Exception ex) {
			return showUnexpectException(ex);
		}

		return CANCEL;
	}

	/**
	 * Grab the role from the database based on the "roleName" passed in.
	 *
	 * @return success if role found
	 * @throws IOException
	 *             can happen when sending a "forbidden" from
	 *             response.sendError()
	 */
	public String edit() throws IOException {

		// if a roleCode is passed in
		if (code != null) {
			prepare();
		} else {
			role = new Role();
		}

		return SUCCESS;
	}

	/**
	 * Default: just returns "success"
	 *
	 * @return "success"
	 */
	public String execute() {
		return SUCCESS;
	}

	/**
	 * Sends roles to "mainMenu" when !from.equals("list"). Sends everyone else
	 * to "cancel"
	 *
	 * @return "mainMenu" or "cancel"
	 */
	public String cancel() {
		return CANCEL;
	}

	/**
	 * Save role
	 *
	 * @return success if everything worked, otherwise input
	 * @throws Exception
	 *             when setting "access denied" fails on response
	 */
	public String save() throws Exception {
		try {
			List<Object> args = new ArrayList<Object>();
			args.add(role.getCode());
			if (role.getVersion() == 0) {
				if (!universalManager.exists(Role.class, role.getCode())) {
					universalManager.save(role);
					saveMessage(getText("role.created", args));
				} else {
					return showRoleExistsException();
				}
			} else {
				universalManager.update(role);
				saveMessage(getText("role.updated", args));
			}
		} catch (HibernateOptimisticLockingFailureException ex) {
			return showStaleObjectStateException();
		} catch (Exception ex) {
			return showUnexpectException(ex);
		}

		prepare();
		return SUCCESS;
	}

	/**
	 * Fetch all roles from database and put into local "roles" variable for
	 * retrieval in the UI.
	 *
	 * @return "success" if no exceptions thrown
	 */
	public String list() {
		query();
		return SUCCESS;
	}

	public String saveRolePermission() {
		try {
			this.roleManager.saveRolePermission(code, PermissionType.valueOf(permissionType), assignedPermissions);
			prepare();
			
			List<Object> args = new ArrayList<Object>();
			args.add(role.getCode());
			saveMessage(getText("role.assignPermissionSuccess", args));
		} catch (Exception e) {
			return showUnexpectException(e);
		}

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void query() {
		String hql = "from Role where 1=1 ";
		List<Object> args = new ArrayList<Object>();

		if (role != null) {
			if (role.getCode() != null && role.getCode().trim().length() != 0) {
				hql += "and code like ? ";
				args.add("%" + role.getCode() + "%");
			}

			if (role.getName() != null && role.getName().trim().length() != 0) {
				hql += "and name like ? ";
				args.add("%" + role.getName() + "%");
			}
		}

		roles = universalManager.findByHql(hql, args.toArray());
	}

	private void prepare() {
		if (role == null && StringHelper.isNotEmpty(code)) {
			role = (Role) this.universalManager.get(Role.class, code);
		}
		
		if (role != null && role.getVersion() != 0) {
			prepareAssignRolePermission();
		}
	}


	private void prepareAssignRolePermission() {
		List<Permission> availablePermissionList = universalManager.findByNativeSql(Permission.class,
				NativeSqlRepository.SELECT_ROLE_AVAILABLE_PERMISSION_STATEMENT,
				new Object[] { permissionType != null ? permissionType : PermissionType.U.toString() });

		List<String> assignedPermissionList = universalManager.findByNativeSql(
				NativeSqlRepository.SELECT_ROLE_ASSIGNED_PERMISSION_STATEMENT,
				new Object[] { permissionType != null ? permissionType : PermissionType.U.toString(), code });

		this.assignedPermissions = assignedPermissionList;
		this.availablePermissions = transferPermissionToLabelValue(availablePermissionList);
	}

	private List<LabelValue> transferPermissionToLabelValue(List<Permission> permissionList) {
		List<LabelValue> lvList = new ArrayList<LabelValue>();
		if (permissionList != null && permissionList.size() > 0) {
			for (Permission permission : permissionList) {
				lvList.add(new LabelValue(permission.getName(), permission.getCode()));
			}
		}

		return lvList;
	}

	private String showRoleExistsException() {
		List<Object> args = new ArrayList<Object>();
		args.add(role.getCode());
		addActionError(getText("role.errors.existingRole", args));

		return INPUT;
	}

	private String showStaleObjectStateException() {
		addActionError(getText("errors.staleObjectStateException"));

		return INPUT;
	}

	private String showUnexpectException(Exception ex) {
		log.error("Unexpect exception occur.", ex);
		List<Object> args = new ArrayList<Object>();
		args.add(ex.getMessage());
		addActionError(getText("errors.unexpectError", args));

		return INPUT;
	}
}
