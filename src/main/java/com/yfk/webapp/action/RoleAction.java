package com.yfk.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import com.yfk.model.Role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Delete the role passed in.
	 *
	 * @return success
	 */
	public String delete() {
		try {
			universalManager.remove(Role.class, role.getCode());
			List<Object> args = new ArrayList<Object>();
			args.add(role.getCode());
			saveMessage(getText("role.deleted", args));
		} catch (Exception ex) {
			return showUnexpectException(ex);
		}

		return SUCCESS;
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
			// lookup the role using code
			role = (Role) universalManager.get(Role.class, code);
		} else {
			role = new Role();
			// role.addRole(new Role(Constants.USER_ROLE));
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
