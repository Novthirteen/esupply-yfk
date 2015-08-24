package com.yfk.service.impl;

import com.yfk.dao.UserDao;
import com.yfk.model.User;
import com.yfk.model.UserList;
import com.yfk.service.UserExistsException;
import com.yfk.service.UserManager;
import com.yfk.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.yfk.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, String> implements UserManager, UserService {
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    @Autowired(required = false)
    private SaltSource saltSource;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }
    
    /**
     * {@inheritDoc}
     */
    public UserList getAllUser() {
    	UserList userList = new UserList();
    	userList.setUsers(userDao.getAllDistinct());
        return userList;
    }

    /**
     * {@inheritDoc}
     */
    public void saveUser(User user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                String currentPassword = userDao.getUserPassword(user.getUsername());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                if (saltSource == null) {
                    // backwards compatibility
                    user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
                    log.warn("SaltSource not set, encrypting password w/o salt");
                } else {
                    user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
                            saltSource.getSalt(user)));
                }
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }

        try {
            this.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(User user) {
        log.debug("removing user: " + user);
        userDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userName) {
        log.debug("removing user: " + userName);
        userDao.remove(userName);
    }
    
    /**
     * {@inheritDoc}
     */
    public User getUser(String username) {
        return (User) userDao.loadUserByUsername(username);
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return (User) userDao.loadUserByUsername(username);
    }
    

    /**
     * {@inheritDoc}
     */
    public List<User> search(String searchTerm) {
        return super.search(searchTerm, User.class);
    }
}
