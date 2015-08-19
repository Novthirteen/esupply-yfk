package com.yfk.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;
import com.yfk.Constants;
import com.yfk.dao.SearchException;
import com.yfk.model.Role;
import com.yfk.model.Role;
import com.yfk.service.RoleExistsException;
import com.yfk.webapp.util.RequestUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Action for facilitating Role Management feature.
 */
public class RoleAction extends BaseAction implements Preparable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Role> roles;
    private Role role;
    private String roleName;
    private String query;

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        // prevent failures on new
        if (getRequest().getMethod().equalsIgnoreCase("post") && (!"".equals(getRequest().getParameter("role.roleCode")))) {
            role = roleManager.getRole(getRequest().getParameter("role.roleCode"));
        }
    }

    /**
     * Holder for roles to display on list screen
     *
     * @return list of roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setQ(String q) {
        this.query = q;
    }

    /**
     * Delete the role passed in.
     *
     * @return success
     */
    public String delete() {
        roleManager.removeRole(role.getCode());
        List<Object> args = new ArrayList<Object>();
        args.add(role.getCode());
        saveMessage(getText("role.deleted", args));

        return SUCCESS;
    }

    /**
     * Grab the role from the database based on the "roleName" passed in.
     *
     * @return success if role found
     * @throws IOException can happen when sending a "forbidden" from response.sendError()
     */
    public String edit() throws IOException {
      
        // if a roleName is passed in
        if (roleName != null) {
            // lookup the role using roleName
            role = roleManager.getRole(roleName);
        } else {
            role = new Role();
            //role.addRole(new Role(Constants.USER_ROLE));
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
     * Sends roles to "mainMenu" when !from.equals("list"). Sends everyone else to "cancel"
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
     * Save role
     *
     * @return success if everything worked, otherwise input
     * @throws Exception when setting "access denied" fails on response
     */
    public String save() throws Exception {


        try {
            roleManager.saveRole(role);
        } catch (AccessDeniedException ade) {
            // thrown by RoleSecurityAdvice configured in aop:advisor roleManagerSecurity
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (RoleExistsException e) {
            return showRoleExistsException();
        }

        
            saveMessage(getText("role.saved"));
            return "mainMenu";
         
    }

    private String showRoleExistsException() {
        List<Object> args = new ArrayList<Object>();
        args.add(role.getCode());
        args.add(role.getName());
        addActionError(getText("errors.existing.role", args));

       
        return INPUT;
    }

    /**
     * Fetch all roles from database and put into local "roles" variable for retrieval in the UI.
     *
     * @return "success" if no exceptions thrown
     */
    public String list() {
        try {
            roles = roleManager.search(query);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            roles = roleManager.getRoles();
        }
        return SUCCESS;
    }

}
