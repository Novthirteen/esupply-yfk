package com.yfk.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yfk.Constants;
import com.yfk.dao.SearchException;
import com.yfk.model.Gender;
import com.yfk.model.LabelValue;
import com.yfk.model.User;
import com.yfk.service.UserExistsException;
import com.yfk.webapp.util.RequestUtil;

/**
 * Action for facilitating User Management feature.
 */
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 6776558938712115191L;
	private List<User> users;
	private User user;
	private String username;

	/**
	 * Holder for users to display on list screen
	 *
	 * @return list of users
	 */
	public List<User> getUsers() {
		return users;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<LabelValue> getGenderList() {
		List<LabelValue> genderList = new ArrayList<LabelValue>();
		genderList.add(new LabelValue(getText("gender.male"), Gender.M.toString()));
		genderList.add(new LabelValue(getText("gender.female"), Gender.F.toString()));

		return genderList;
	}

	/**
	 * Delete the user passed in.
	 *
	 * @return success
	 */
	public String delete() {
		userManager.removeUser(user.getUsername());
		List<Object> args = new ArrayList<Object>();
		args.add(user.getFullName());
		saveMessage(getText("user.deleted", args));

		return SUCCESS;
	}

	/**
	 * Grab the user from the database based on the "username" passed in.
	 *
	 * @return success if user found
	 * @throws IOException
	 *             can happen when sending a "forbidden" from
	 *             response.sendError()
	 */
	public String edit() throws IOException {
		HttpServletRequest request = getRequest();
		boolean editProfile = request.getRequestURI().contains("editProfile");

		// if URL is "editProfile" - make sure it's the current user
		if (editProfile && ((request.getParameter("username") != null) || (request.getParameter("from") != null))) {
			ServletActionContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
			log.warn("User '" + request.getRemoteUser() + "' is trying to edit user '"
					+ request.getParameter("username") + "'");
			return null;
		}

		// if a username is passed in
		if (username != null) {
			// lookup the user using username
			user = userManager.getUser(username);
		} else if (editProfile) {
			user = userManager.getUserByUsername(request.getRemoteUser());
		} else {
			user = new User();
			// user.addRole(new Role(Constants.USER_ROLE));
		}

		if (user.getUsername() != null) {
			user.setConfirmPassword(user.getPassword());

			// if user logged in with remember me, display a warning that they
			// can't change passwords
			log.debug("checking for remember me login...");

			AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
			SecurityContext ctx = SecurityContextHolder.getContext();

			if (ctx != null) {
				Authentication auth = ctx.getAuthentication();

				if (resolver.isRememberMe(auth)) {
					getSession().setAttribute("cookieLogin", "true");
					saveMessage(getText("userProfile.cookieLogin"));
				}
			}
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
	 * Sends users to "mainMenu" when !from.equals("list"). Sends everyone else
	 * to "cancel"
	 *
	 * @return "mainMenu" or "cancel"
	 */
	public String cancel() {
		if (!"list".equals(from)) {
			return "mainMenu";
		}
		return "cancel";
	}

	/**
	 * Save user
	 *
	 * @return success if everything worked, otherwise input
	 * @throws Exception
	 *             when setting "access denied" fails on response
	 */
	public String save() throws Exception {

		Integer originalVersion = user.getVersion();

		boolean isNew = ("".equals(getRequest().getParameter("user.version")));
		// only attempt to change roles if user is admin
		// for other users, prepare() method will handle populating
		if (getRequest().isUserInRole(Constants.ADMIN_ROLE)) {
			// user.getRoles().clear(); // APF-788: Removing roles from user
			// doesn't work
			String[] userRoles = getRequest().getParameterValues("userRoles");

			for (int i = 0; userRoles != null && i < userRoles.length; i++) {
				String roleName = userRoles[i];
				try {
					// user.addRole(roleManager.getRole(roleName));
				} catch (DataIntegrityViolationException e) {
					return showUserExistsException(originalVersion);
				}
			}
		}

		try {
			if (isNew) {
				userManager.saveUser(user);
			} else {
				userManager.update(user);
			}
		} catch (AccessDeniedException ade) {
			// thrown by UserSecurityAdvice configured in aop:advisor
			// userManagerSecurity
			log.warn(ade.getMessage());
			getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		} catch (UserExistsException e) {
			return showUserExistsException(originalVersion);
		}

		if (!"list".equals(from)) {
			// add success messages
			saveMessage(getText("user.saved"));
			return "mainMenu";
		} else {
			// add success messages
			List<Object> args = new ArrayList<Object>();
			args.add(user.getFullName());
			if (isNew) {
				saveMessage(getText("user.added", args));
				// Send an account information e-mail
				mailMessage.setSubject(getText("signup.email.subject"));
				try {
					sendUserMessage(user, getText("newuser.email.message", args), RequestUtil.getAppURL(getRequest()));
				} catch (MailException me) {
					addActionError(me.getCause().getLocalizedMessage());
				}
				return SUCCESS;
			} else {
				user.setConfirmPassword(user.getPassword());
				saveMessage(getText("user.updated.byAdmin", args));
				return INPUT;
			}
		}
	}

	private String showUserExistsException(Integer originalVersion) {
		List<Object> args = new ArrayList<Object>();
		args.add(user.getUsername());
		args.add(user.getEmail());
		addActionError(getText("user.errors.existingUser", args));

		// reset the version # to what was passed in
		user.setVersion(originalVersion);
		// redisplay the unencrypted passwords
		user.setPassword(user.getConfirmPassword());
		return INPUT;
	}

	/**
	 * Fetch all users from database and put into local "users" variable for
	 * retrieval in the UI.
	 *
	 * @return "success" if no exceptions thrown
	 */
	public String list() {
		try {
			query();
		} catch (SearchException se) {
			addActionError(se.getMessage());
			users = userManager.getUsers();
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void query() {

		String hql = "from User where 1=1 ";
		List args = new ArrayList();

		if (user != null) {
			if (user.getUsername() != null && user.getUsername().trim().length() != 0) {
				hql += "and username like ? ";
				args.add("%" + user.getUsername() + "%");
			}

			if (user.getFirstName() != null && user.getFirstName().trim().length() != 0) {
				hql += "and firstName like ? ";
				args.add("%" + user.getFirstName() + "%");
			}
			if (user.getLastName() != null && user.getLastName().trim().length() != 0) {
				hql += "and firstName like ? ";
				args.add("%" + user.getLastName() + "%");
			}
		}

		if (args.size() > 0) {
			Object[] objs = new Object[args.size()];

			for (int i = 0; i < args.size(); i++) {
				objs[i] = args.get(i);
			}

			users = universalManager.findByHql(hql, objs);
		} else {
			users = universalManager.getAll(User.class);
		}

	}
}
