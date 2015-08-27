package com.yfk.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yfk.model.Menu;
import com.yfk.model.UserMenu;
import com.yfk.webapp.util.PrincipalNullException;

@WebService
@Path("/userMenus")
public interface UserMenuService {

    @GET
    @Path("getusermenus")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserMenu> getUserMenus() throws PrincipalNullException;
}
