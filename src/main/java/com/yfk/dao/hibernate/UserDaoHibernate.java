package com.yfk.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;
import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.yfk.dao.UserDao;
import com.yfk.model.Menu;
import com.yfk.model.User;
import com.yfk.model.UserAuthority;
import com.yfk.model.UserMenu;
import com.yfk.service.UniversalManager;
import com.yfk.webapp.action.NativeSqlRepository;
import com.yfk.webapp.util.AppContextUtil;

/**
 * This class interacts with Hibernate session to save/delete and retrieve User
 * objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler</a> Extended to
 *         implement Acegi UserDetailsService interface by David Carter
 *         david@carter.net Modified by <a href="mailto:bwnoll@gmail.com">Bryan
 *         Noll</a> to work with the new BaseDaoHibernate implementation that
 *         uses generics. Modified by jgarcia (updated to hibernate 4)
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, String>implements UserDao, UserDetailsService {

	@Autowired
	private UniversalManager universalManager;

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public UserDaoHibernate() {
		super(User.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Query qry = getSession().createQuery("from User u order by upper(u.username)");
		return qry.list();
	}

	/**
	 * {@inheritDoc}
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("user '" + username + "' not found...");
		} else {
			User user = (User) users.get(0);

			loadUserAuthority(user);
			loadUserMenu(user);
			return (UserDetails) user;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserPassword(String userName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
		return jdbcTemplate.queryForObject("select password from " + table.name() + " where userName=?", String.class,
				userName);
	}

	private void loadUserAuthority(User user) {
		user.setUserAuthorities(getSession().createCriteria(UserAuthority.class)
				.add(Restrictions.eq("username", user.getUsername())).list());
	}

	private void loadUserMenu(User user) {
		List<Menu> authorityMenuList = universalManager.findByNativeSql(Menu.class,
				NativeSqlRepository.SELECT_USER_MENU_STATEMENT,
				new Object[] { user.getUsername(), user.getUsername() });

		if (CollectionHelper.isNotEmpty(authorityMenuList)) {
			List<UserMenu> userMenus = new ArrayList<UserMenu>();
			for (Menu menu : authorityMenuList) {
				if (StringHelper.isEmpty(menu.getParent())) {
					UserMenu userMenu = this.assemblyUserMenu(menu);
					userMenus.add(userMenu);
					for (Menu childMenu : authorityMenuList) {
						if (menu.getCode().equals(childMenu.getParent())) {
							userMenu.addItem(this.assemblyUserMenu(childMenu));
						}
					}
				}
			}

			user.setUserMenus(userMenus);
		}
	}

	private UserMenu assemblyUserMenu(Menu menu) {
		ServletContext context = AppContextUtil.getServletContext();

		UserMenu userMenu = new UserMenu();
		userMenu.setValue(menu.getCode());
		userMenu.setText(menu.getName());
		if (StringHelper.isNotEmpty(menu.getImageUrl())) {
			userMenu.setImageUrl(context.getContextPath() + menu.getImageUrl());
		}
		userMenu.setSequence(menu.getSequence());
		if (StringHelper.isNotEmpty(menu.getPageUrl())) {
			userMenu.setUrl(context.getContextPath() + menu.getPageUrl());
		}

		return userMenu;
	}
}
