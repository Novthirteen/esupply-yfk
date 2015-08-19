package com.yfk.dao.hibernate;

import com.yfk.dao.RoleDao;
import com.yfk.model.Role;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


/**
 * This class interacts with hibernate session to save/delete and
 * retrieve Role objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author jgarcia (updated to hibernate 4)
 */
@Repository
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public RoleDaoHibernate() {
        super(Role.class);
    }

    /**
     * {@inheritDoc}
     */
    public Role getRoleByCode(String code) {
        List roles = getSession().createCriteria(Role.class).add(Restrictions.eq("code", code)).list();
        if (roles.isEmpty()) {
            return null;
        } else {
            return (Role) roles.get(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String code) {
        Object role = getRoleByCode(code);
        Session session = getSessionFactory().getCurrentSession();
        session.delete(role);
    }
}
