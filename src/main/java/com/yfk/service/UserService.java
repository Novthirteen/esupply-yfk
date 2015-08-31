package com.yfk.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yfk.model.User;
import com.yfk.model.UserMenu;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/users")
public interface UserService {

	/**
	 * Retrieves a list of all users.
	 *
	 * @return List
	 */
	@GET
	@Path("getUsers")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> getUsers();

	/**
	 * Retrieves a user by userId. An exception is thrown if user not found
	 *
	 * @param userName
	 *            for the user
	 * @return User
	 */
	@GET
	@Path("getuser/{userName}")
	public User getUser(@PathParam("userName") String userName);

	/**
	 * Finds a user by their username.
	 *
	 * @param username
	 *            the user's username used to login
	 * @return User a populated user object
	 */
	public User getUserByUsername(@PathParam("username") String username);

	/**
	 * Saves a user's information
	 *
	 * @param user
	 *            the user's information
	 * @return updated user
	 * @throws UserExistsException
	 *             thrown when user already exists
	 */
	@POST
	void saveUser(User user) throws UserExistsException;

	/**
	 * Removes a user from the database by their userId
	 *
	 * @param userId
	 *            the user's id
	 */
	@DELETE
	void removeUser(String userId);

	/**
	 * Retrieves a list of all users.
	 *
	 * @return List
	 */
	@GET
	@Path("getUserMenus")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<UserMenu> getUserMenus();
}
